package com.stefansavev.core.serialization

import java.io._

object TupleSerializers {

  class Tuple2Serializer[A1, A2](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2]) extends TypedSerializer[(A1, A2)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
    }

    def fromBinary(inputStream: InputStream): (A1, A2) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      (a1, a2)
    }

    def name: String = s"tuple2(${serA1.name}, ${serA2.name})"
  }

  implicit def tuple2Serializer[A1, A2](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2]): Tuple2Serializer[A1, A2] = {
    new Tuple2Serializer[A1, A2](serA1, serA2)
  }


  //---------------------------------------------------------

  class Tuple3Serializer[A1, A2, A3](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3]) extends TypedSerializer[(A1, A2, A3)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      (a1, a2, a3)
    }

    def name: String = s"tuple3(${serA1.name}, ${serA2.name}, ${serA3.name})"
  }

  implicit def tuple3Serializer[A1, A2, A3](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3]): Tuple3Serializer[A1, A2, A3] = {
    new Tuple3Serializer[A1, A2, A3](serA1, serA2, serA3)
  }


  //---------------------------------------------------------

  class Tuple4Serializer[A1, A2, A3, A4](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4]) extends TypedSerializer[(A1, A2, A3, A4)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      (a1, a2, a3, a4)
    }

    def name: String = s"tuple4(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name})"
  }

  implicit def tuple4Serializer[A1, A2, A3, A4](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4]): Tuple4Serializer[A1, A2, A3, A4] = {
    new Tuple4Serializer[A1, A2, A3, A4](serA1, serA2, serA3, serA4)
  }


  //---------------------------------------------------------

  class Tuple5Serializer[A1, A2, A3, A4, A5](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5]) extends TypedSerializer[(A1, A2, A3, A4, A5)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      (a1, a2, a3, a4, a5)
    }

    def name: String = s"tuple5(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name})"
  }

  implicit def tuple5Serializer[A1, A2, A3, A4, A5](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5]): Tuple5Serializer[A1, A2, A3, A4, A5] = {
    new Tuple5Serializer[A1, A2, A3, A4, A5](serA1, serA2, serA3, serA4, serA5)
  }


  //---------------------------------------------------------

  class Tuple6Serializer[A1, A2, A3, A4, A5, A6](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6)
    }

    def name: String = s"tuple6(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name})"
  }

  implicit def tuple6Serializer[A1, A2, A3, A4, A5, A6](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6]): Tuple6Serializer[A1, A2, A3, A4, A5, A6] = {
    new Tuple6Serializer[A1, A2, A3, A4, A5, A6](serA1, serA2, serA3, serA4, serA5, serA6)
  }


  //---------------------------------------------------------

  class Tuple7Serializer[A1, A2, A3, A4, A5, A6, A7](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7)
    }

    def name: String = s"tuple7(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}, ${serA7.name})"
  }

  implicit def tuple7Serializer[A1, A2, A3, A4, A5, A6, A7](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7]): Tuple7Serializer[A1, A2, A3, A4, A5, A6, A7] = {
    new Tuple7Serializer[A1, A2, A3, A4, A5, A6, A7](serA1, serA2, serA3, serA4, serA5, serA6, serA7)
  }


  //---------------------------------------------------------

  class Tuple8Serializer[A1, A2, A3, A4, A5, A6, A7, A8](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8)
    }

    def name: String = s"tuple8(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}, ${serA7.name},  ${serA8.name})"
  }

  implicit def tuple8Serializer[A1, A2, A3, A4, A5, A6, A7, A8](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8]): Tuple8Serializer[A1, A2, A3, A4, A5, A6, A7, A8] = {
    new Tuple8Serializer[A1, A2, A3, A4, A5, A6, A7, A8](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8)
  }


  //---------------------------------------------------------

  class Tuple9Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8, A9)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8, A9)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
      serA9.toBinary(outputStream, input._9)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8, A9) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      val a9 = serA9.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8, a9)
    }

    def name: String = s"tuple9(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}, ${serA7.name}, ${serA8.name}, ${serA9.name})"
  }

  implicit def tuple9Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9]): Tuple9Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9] = {
    new Tuple9Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8, serA9)
  }


  //---------------------------------------------------------

  class Tuple10Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
      serA9.toBinary(outputStream, input._9)
      serA10.toBinary(outputStream, input._10)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      val a9 = serA9.fromBinary(inputStream)
      val a10 = serA10.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)
    }

    def name: String = s"tuple10(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}," +
      s"${serA7.name}, ${serA8.name}, ${serA9.name}, ${serA10.name})"
  }

  implicit def tuple10Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10]): Tuple10Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10] = {
    new Tuple10Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8, serA9, serA10)
  }


  //---------------------------------------------------------

  class Tuple11Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
      serA9.toBinary(outputStream, input._9)
      serA10.toBinary(outputStream, input._10)
      serA11.toBinary(outputStream, input._11)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      val a9 = serA9.fromBinary(inputStream)
      val a10 = serA10.fromBinary(inputStream)
      val a11 = serA11.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)
    }

    def name: String = s"tuple11(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}," +
      s"${serA7.name}, ${serA8.name}, ${serA9.name}, ${serA10.name}, ${serA11.name})"
  }

  implicit def tuple11Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11]): Tuple11Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11] = {
    new Tuple11Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8, serA9, serA10, serA11)
  }


  //---------------------------------------------------------

  class Tuple12Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11], serA12: TypedSerializer[A12]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
      serA9.toBinary(outputStream, input._9)
      serA10.toBinary(outputStream, input._10)
      serA11.toBinary(outputStream, input._11)
      serA12.toBinary(outputStream, input._12)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      val a9 = serA9.fromBinary(inputStream)
      val a10 = serA10.fromBinary(inputStream)
      val a11 = serA11.fromBinary(inputStream)
      val a12 = serA12.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12)
    }

    def name: String = s"tuple12(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}," +
      s"${serA7.name}, ${serA8.name}, ${serA9.name}, ${serA10.name}, ${serA11.name}, ${serA12.name})"
  }

  implicit def tuple12Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11], serA12: TypedSerializer[A12]): Tuple12Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12] = {
    new Tuple12Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8, serA9, serA10, serA11, serA12)
  }


  //---------------------------------------------------------

  class Tuple13Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11], serA12: TypedSerializer[A12], serA13: TypedSerializer[A13]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
      serA9.toBinary(outputStream, input._9)
      serA10.toBinary(outputStream, input._10)
      serA11.toBinary(outputStream, input._11)
      serA12.toBinary(outputStream, input._12)
      serA13.toBinary(outputStream, input._13)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      val a9 = serA9.fromBinary(inputStream)
      val a10 = serA10.fromBinary(inputStream)
      val a11 = serA11.fromBinary(inputStream)
      val a12 = serA12.fromBinary(inputStream)
      val a13 = serA13.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13)
    }

    def name: String = s"tuple13(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}," +
      s"${serA7.name}, ${serA8.name}, ${serA9.name}, ${serA10.name}, ${serA11.name}, ${serA12.name}, ${serA13.name})"
  }

  implicit def tuple13Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11], serA12: TypedSerializer[A12], serA13: TypedSerializer[A13]): Tuple13Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13] = {
    new Tuple13Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8, serA9, serA10, serA11, serA12, serA13)
  }


  //---------------------------------------------------------

  class Tuple14Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14](serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11], serA12: TypedSerializer[A12], serA13: TypedSerializer[A13], serA14: TypedSerializer[A14]) extends TypedSerializer[(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14)] {

    def toBinary(outputStream: OutputStream, input: (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14)): Unit = {
      serA1.toBinary(outputStream, input._1)
      serA2.toBinary(outputStream, input._2)
      serA3.toBinary(outputStream, input._3)
      serA4.toBinary(outputStream, input._4)
      serA5.toBinary(outputStream, input._5)
      serA6.toBinary(outputStream, input._6)
      serA7.toBinary(outputStream, input._7)
      serA8.toBinary(outputStream, input._8)
      serA9.toBinary(outputStream, input._9)
      serA10.toBinary(outputStream, input._10)
      serA11.toBinary(outputStream, input._11)
      serA12.toBinary(outputStream, input._12)
      serA13.toBinary(outputStream, input._13)
      serA14.toBinary(outputStream, input._14)
    }

    def fromBinary(inputStream: InputStream): (A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) = {
      val a1 = serA1.fromBinary(inputStream)
      val a2 = serA2.fromBinary(inputStream)
      val a3 = serA3.fromBinary(inputStream)
      val a4 = serA4.fromBinary(inputStream)
      val a5 = serA5.fromBinary(inputStream)
      val a6 = serA6.fromBinary(inputStream)
      val a7 = serA7.fromBinary(inputStream)
      val a8 = serA8.fromBinary(inputStream)
      val a9 = serA9.fromBinary(inputStream)
      val a10 = serA10.fromBinary(inputStream)
      val a11 = serA11.fromBinary(inputStream)
      val a12 = serA12.fromBinary(inputStream)
      val a13 = serA13.fromBinary(inputStream)
      val a14 = serA14.fromBinary(inputStream)
      (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14)
    }

    def name: String = s"tuple14(${serA1.name}, ${serA2.name}, ${serA3.name}, ${serA4.name}, ${serA5.name}, ${serA6.name}," +
      s"${serA7.name}, ${serA8.name}, ${serA9.name}, ${serA10.name}, ${serA11.name}, ${serA12.name}, ${serA13.name}, ${serA14.name})"
  }

  implicit def tuple14Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14](implicit serA1: TypedSerializer[A1], serA2: TypedSerializer[A2], serA3: TypedSerializer[A3], serA4: TypedSerializer[A4], serA5: TypedSerializer[A5], serA6: TypedSerializer[A6], serA7: TypedSerializer[A7], serA8: TypedSerializer[A8], serA9: TypedSerializer[A9], serA10: TypedSerializer[A10], serA11: TypedSerializer[A11], serA12: TypedSerializer[A12], serA13: TypedSerializer[A13], serA14: TypedSerializer[A14]): Tuple14Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14] = {
    new Tuple14Serializer[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14](serA1, serA2, serA3, serA4, serA5, serA6, serA7, serA8, serA9, serA10, serA11, serA12, serA13, serA14)
  }

  //---------------------------------------------------------
}
