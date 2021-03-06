package com.stefansavev.similaritysearch.implementation

import java.io.{File, FileFilter}
import java.util
import java.util.Random

import com.stefansavev.randomprojections.datarepr.dense._
import com.stefansavev.randomprojections.datarepr.dense.store._
import com.stefansavev.randomprojections.dimensionalityreduction.svd.{OnlineSVDFitter, SVDTransform}
import com.stefansavev.randomprojections.implementation._
import com.stefansavev.randomprojections.implementation.bucketsearch.{PointScoreSettings, PriorityQueueBasedBucketSearchSettings}
import com.stefansavev.randomprojections.implementation.indexing.IndexBuilder
import com.stefansavev.randomprojections.serialization.{DataFrameViewSerializationExt, RandomTreesSerialization}
import com.stefansavev.randomprojections.utils.Utils
import com.stefansavev.similaritysearch.VectorType.StorageSize
import com.stefansavev.similaritysearch.{SimilaritySearchItem, SimilaritySearchResult}
import com.typesafe.scalalogging.StrictLogging

class FuzzySearchIndexWrapper(trees: RandomTrees, dataset: DataFrameView) {
  val searcherSettings = SearcherSettings(
    bucketSearchSettings = PriorityQueueBasedBucketSearchSettings(numberOfRequiredPointsPerTree = 100),
    pointScoreSettings = PointScoreSettings(topKCandidates = 100, rescoreExactlyTopK = 100),
    randomTrees = trees,
    trainingSet = dataset)

  val searcher = new NonThreadSafeSearcher(searcherSettings)

  def bruteForceSearch(numNeighbors: Int, query: Array[Double]): java.util.List[SimilaritySearchResult] = {
    import java.util.PriorityQueue
    val pq = new PriorityQueue[PointScore]()
    var j = 0
    val numRows = dataset.numRows
    while (j < numRows) {
      val score = dataset.cosineForNormalizedData(query, j)
      if (pq.size() < numNeighbors) {
        pq.add(new PointScore(j, score))
      }
      else {
        //there should be exactly k items in pq
        val minAcceptedScore = pq.peek().score
        if (score > minAcceptedScore) {
          pq.remove() //remove min
          pq.add(new PointScore(j, score))
        }
      }
      j += 1
    }
    val n = pq.size()
    val sortedPoints = Array.ofDim[PointScore](n)
    j = 0
    while (pq.size() > 0) {
      sortedPoints(n - j - 1) = pq.remove()
      j += 1
    }

    val result = new java.util.ArrayList[SimilaritySearchResult]()
    var i = 0
    while (i < sortedPoints.length) {
      val idAndScore = sortedPoints(i)
      val id = idAndScore.pointId
      val name = dataset.getName(id)
      val label = dataset.getLabel(id)
      result.add(new SimilaritySearchResult(name, label, idAndScore.score))
      i += 1
    }
    result
  }

  def getNearestNeighborsByQuery(numNeighbors: Int, query: Array[Double]): java.util.List[SimilaritySearchResult] = {
    val knns = searcher.getNearestNeighborsByVector(query, numNeighbors)
    val neighbors = knns.neighbors
    val dataset = this.dataset
    val result = new java.util.ArrayList[SimilaritySearchResult]()
    var i = 0
    while (i < neighbors.length) {
      val nn = neighbors(i)
      val id = nn.neighborId
      val name = dataset.getName(id)
      val label = nn.label
      result.add(new SimilaritySearchResult(name, label, nn.dist))
      i += 1
    }
    result
  }

  def getItemByName(name: String): SimilaritySearchItem = {
    val i = dataset.getRowIdByName(name)
    getItemById(i)
  }

  def getItemById(i: Int): SimilaritySearchItem = {
    new SimilaritySearchItem(dataset.getName(i), dataset.getLabel(i), dataset.getPointAsDenseVector(i))
  }

  def getDimension(): Int = {
    dataset.numCols
  }

  def getItems(): java.util.Iterator[SimilaritySearchItem] = {
    new util.Iterator[SimilaritySearchItem] {
      val numRows = dataset.numRows
      var i = 0

      override def next(): SimilaritySearchItem = {
        val item = getItemById(i)
        i += 1
        item
      }

      override def remove(): Unit = {
        throw new IllegalStateException("remove is not supported")
      }

      override def hasNext: Boolean = {
        i < numRows
      }
    }
  }
}

object FuzzySearchIndexWrapper {
  val fileVersion = "0_1"
  val treesSubDir = s"fuzzysearch_trees_dir_${fileVersion}"
  val datasetFile = s"fuzzysearch_dataset_file_${fileVersion}"
  val datasetPartitionsDir = s"fuzzysearch_dataset_partitions_${fileVersion}"
  val datasetPartitionFile = LazyLoadValueStore.partitionFileNamePrefix
  val signaturePartitionsDir = s"fuzzysearch_signature_partitions_${fileVersion}"
  val signaturePartitionFile = DiskBackedOnlineSignatureVectorsUtils.partitionFileNamePrefix
  val randomTreesModelFile = RandomTreesSerialization.Implicits.modelFileName
  val randomTreesIndexFile = RandomTreesSerialization.Implicits.indexFileName
  val randomTreesPartitionsDir = s"fuzzysearch_tree_partitions_${fileVersion}"
  val randomTreesPartitionFile = BucketCollectorImplUtils.partitionFileSuffix

  def open(fileName: String): FuzzySearchIndexWrapper = {
    import DataFrameViewSerializationExt._
    import RandomTreesSerialization.Implicits._

    val treesFullDir = new File(fileName, treesSubDir)
    val datasetFullFile = new File(fileName, datasetFile)

    val trees = RandomTrees.fromFile(treesFullDir)
    val dataset = DataFrameView.fromFile(datasetFullFile)
    new FuzzySearchIndexWrapper(trees, dataset)
  }
}

class FuzzySearchIndexBuilderWrapper(backingFile: String, dim: Int, numTrees: Int, valueSize: StorageSize) extends StrictLogging {
  if (dim < 8) {
    throw new IllegalStateException("The data dimension has to be greater or equal to 8")
  }

  def checkDirectories(dirName: String): Unit = {
    val file = new File(dirName)
    if (!file.exists()) {
      throw new IllegalStateException(s"Directory should exist: ${dirName}")
    }

    if (!file.isDirectory()) {
      throw new IllegalStateException(s"File is not a directory ${dirName}")
    }

    def cleanDir(subDir: String, acceptsFile: File => Boolean): Unit = {
      val fullDir = new File(dirName, subDir)
      if (!fullDir.exists()) {
        fullDir.mkdir()
      }
      val unrecognizedFiles = fullDir.listFiles(new FileFilter {
        override def accept(pathName: File): Boolean = {
          !acceptsFile(pathName)
        }
      })
      if (unrecognizedFiles.length > 0) {
        val unrecognizedFile = unrecognizedFiles(0)
        Utils.failWith(s"Directory ${fullDir} contains an unrecognized file: " + unrecognizedFile.getName)
      }
      val filesToBeDeleted = fullDir.listFiles(new FileFilter {
        override def accept(pathName: File): Boolean = {
          acceptsFile(pathName)
        }
      })
      filesToBeDeleted.foreach(f => {
        logger.info(s"FILE TO BE DELETED $f")
        f.delete()
      })
      if (fullDir.listFiles().length > 0) {
        Utils.failWith(s"Directory contains files that could not be deleted")
      }
    }

    cleanDir(FuzzySearchIndexWrapper.treesSubDir, file => {
      file.getName.startsWith(FuzzySearchIndexWrapper.randomTreesModelFile) ||
        file.getName.startsWith(FuzzySearchIndexWrapper.randomTreesIndexFile)
    })

    cleanDir(FuzzySearchIndexWrapper.datasetPartitionsDir, file => file.getName.startsWith(FuzzySearchIndexWrapper.datasetPartitionFile))
    cleanDir(FuzzySearchIndexWrapper.signaturePartitionsDir, file => file.getName.startsWith(FuzzySearchIndexWrapper.signaturePartitionFile))
    cleanDir(FuzzySearchIndexWrapper.randomTreesPartitionsDir, file => file.getName.startsWith(FuzzySearchIndexWrapper.randomTreesPartitionFile))
  }

  checkDirectories(backingFile)

  val storageType = valueSize match {
    case StorageSize.Double => {
      StoreBuilderAsDoubleType
    }
    case StorageSize.TwoBytes => {
      StoreBuilderAsBytesType
    }
    case StorageSize.SingleByte => {
      StoreBuilderAsSingleByteType
    }
  }

  val datasetBackingDir = new File(backingFile, FuzzySearchIndexWrapper.datasetPartitionsDir).getAbsolutePath
  val storageWithFile = new AsyncStoreBuilderType(datasetBackingDir, storageType)
  //val storageWithFile = new LazyLoadStoreBuilderType(datasetBackingDir, storageType)

  val columnIds = Array.range(0, dim)
  val header = ColumnHeaderBuilder.build("label", columnIds.map(i => ("f" + i, i)), true, storageWithFile)
  val builder = DenseRowStoredMatrixViewBuilderFactory.create(header)
  val onlineSVDFitter = new OnlineSVDFitter(dim)
  val sigSize = 16
  val signatureBackedDir = new File(backingFile, FuzzySearchIndexWrapper.signaturePartitionsDir).getAbsolutePath


  val onlineSigVecs = new AsyncSignatureVectors(signatureBackedDir, new Random(48186816), sigSize, dim)
  //val onlineSigVecs = new DiskBackedOnlineSignatureVectors(signatureBackedDir, new Random(48186816), sigSize, dim)

  def addItem(name: String, label: Int, dataPoint: Array[Double]): Unit = {
    onlineSVDFitter.pass(dataPoint)
    onlineSigVecs.pass(dataPoint)
    builder.addRow(name, label, columnIds, dataPoint)
  }

  def build(): FuzzySearchIndexWrapper = {
    val numRows = builder.currentRowId
    val indexes = PointIndexes(Array.range(0, numRows))
    val dataset = new DataFrameView(indexes, builder.asInstanceOf[DenseRowStoredMatrixViewBuilder].build())

    val svdTransform = onlineSVDFitter.finalizeFit().asInstanceOf[SVDTransform]

    val randomTreeSettings = IndexSettings(
      maxPntsPerBucket = 50,
      numTrees = numTrees,
      maxDepth = None,
      projectionStrategyBuilder = ProjectionStrategies.splitIntoKRandomProjection(8),
      reportingDistanceEvaluator = ReportingDistanceEvaluators.cosineOnOriginalData(),
      randomSeed = 39393,
      signatureSize = sigSize
    )

    val signatures = onlineSigVecs.buildPointSignatures()
    val trees = (Utils.timed("Create trees", {
      val treePartitionsDir = new File(backingFile, FuzzySearchIndexWrapper.randomTreesPartitionsDir).getAbsolutePath
      val svdDim = Math.min(32, HadamardUtils.roundDown(dim))
      IndexBuilder.buildWithSVDAndRandomRotation(treePartitionsDir, svdDim, settings = randomTreeSettings, dataFrameView = dataset, Some(svdTransform), Some(signatures))
    })(logger)).result

    def save(fileName: String): Unit = {
      import DataFrameViewSerializationExt._
      import RandomTreesSerialization.Implicits._
      val file = new File(fileName)
      if (!file.exists()) {
        throw new IllegalStateException(s"Directory should exist: ${fileName}")
      }

      if (!file.isDirectory()) {
        throw new IllegalStateException(s"File is not a directory ${fileName}")
      }
      val treesFullDir = new File(fileName, FuzzySearchIndexWrapper.treesSubDir)
      if (!treesFullDir.exists()) {
        treesFullDir.mkdir()
      }

      val datasetFullFile = new File(fileName, FuzzySearchIndexWrapper.datasetFile)
      trees.toFile(treesFullDir)
      dataset.toFile(datasetFullFile)
    }
    save(backingFile)

    new FuzzySearchIndexWrapper(trees, dataset)
  }
}

