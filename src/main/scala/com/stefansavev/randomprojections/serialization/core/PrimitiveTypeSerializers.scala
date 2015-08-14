package com.stefansavev.randomprojections.serialization.core

import java.io.{InputStream, OutputStream}

import com.stefansavev.randomprojections.serialization.{DoubleArraySerializer, IntArraySerializer, DoubleSerializer, IntSerializer}

object PrimitiveTypeSerializers {

  implicit object TypedIntSerializer extends TypedSerializer[Int] {
    def toBinary(outputStream: OutputStream, input: Int): Unit = {
      IntSerializer.write(outputStream, input)
    }

    def fromBinary(inputStream: InputStream): Int = {
      IntSerializer.read(inputStream)
    }
  }

  implicit object TypedDoubleSerializer extends TypedSerializer[Double] {
    def toBinary(outputStream: OutputStream, input: Double): Unit = {
      DoubleSerializer.write(outputStream, input)
    }

    def fromBinary(inputStream: InputStream): Double = {
      DoubleSerializer.read(inputStream)
    }
  }

  implicit object TypedIntArraySerializer extends TypedSerializer[Array[Int]]{
    def toBinary(outputStream: OutputStream, input: SerializerType): Unit = {
      IntArraySerializer.write(outputStream, input)
    }

    def fromBinary(inputStream: InputStream): SerializerType = {
      IntArraySerializer.read(inputStream)
    }
  }

  implicit object TypedDoubleArraySerializer extends TypedSerializer[Array[Double]]{
    def toBinary(outputStream: OutputStream, input: SerializerType): Unit = {
      DoubleArraySerializer.write(outputStream, input)
    }

    def fromBinary(inputStream: InputStream): SerializerType = {
      DoubleArraySerializer.read(inputStream)
    }
  }
}