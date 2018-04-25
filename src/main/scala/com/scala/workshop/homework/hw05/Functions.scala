package com.scala.workshop.homework.hw05

import org.scalatest.{FlatSpec, Matchers}

case class Point2D(x: Int, y: Int)

object Functions {

  val maxSumProd: (Int, Int, Int) => String = ??? // TODO: dokończ

  val sumOfDigits: Int => Int = ??? // TODO: dokończ

  val distance2D: (Point2D,Point2D) => Double = ??? // TODO: dokończ

  val filterOddOnly: List[Int] => List[Int] = ??? // TODO: dokończ

  val expList: List[Int] => List[Int] = ??? // TODO: dokończ

  val expOfOddOnly: List[Int] => List[Int] = ??? // TODO: dokończ

  def areEqualFor(/* uzupełnij */) = ??? // TODO: dokończ

  def applyAll(/* uzupełnij */) = ??? // TODO: dokończ

}

class FunctinsTest extends FlatSpec with Matchers {

  behavior of "maxSumProd"

  it should "return sum if sum is greater" in {
    Functions.maxSumProd(1, 1, 2) shouldBe "sum"
  }

  it should "return product if sum is greater" in {
    Functions.maxSumProd(2, 2, 2) shouldBe "product"
  }

  behavior of "sumOfDigits"

  it should "return 7 for 1111111" in {
    Functions.sumOfDigits(1111111) shouldBe 7
  }

  it should "return 7 for 1234567" in {
    Functions.sumOfDigits(1234567) shouldBe 28
  }

  it should "return 7 for 10001" in {
    Functions.sumOfDigits(10001) shouldBe 2
  }

  behavior of "distance2D"

  it should "return proper distance for (1,1) and (3,1)" in {
    Functions.distance2D(Point2D(1,1), Point2D(3,1)) shouldBe 2.0
  }

  it should "return proper distance for (1,1) and (1,1)" in {
    Functions.distance2D(Point2D(1,1), Point2D(1,1)) shouldBe 0.0
  }

  it should "return proper distance for (1,1) and (7,9)" in {
    Functions.distance2D(Point2D(1,1), Point2D(7,9)) shouldBe 10.0
  }

  it should "return proper distance for (1,1) and (-3,-4)" in {
    Functions.distance2D(Point2D(1,1), Point2D(-3,-4)) shouldBe 6.4 +- 0.01
  }

  behavior of "filterOddOnly"

  it should "return proper values in case of (1, 2, 3, 4, 5, 6, 7)" in {
    Functions.filterOddOnly(List(1, 2, 3, 4, 5, 6, 7)) should contain only (1, 3, 5, 7)
  }

  it should "return empty list in case of (2, 4, 6)" in {
    Functions.filterOddOnly(List(2, 4, 6)) shouldBe empty
  }

  behavior of "expList"

  it should "return proper values for (2, 5, 8)" in {
    Functions.expList(List(2, 5, 8)) shouldBe List(4, 25, 64)
  }

  it should "return proper values for (-1, -2, -4)" in {
    Functions.expList(List(-1, -2, -4)) shouldBe List(1, 4, 16)
  }

  behavior of "expOfOddOnly"

  it should "return proper values for (2, 5, 8)" in {
    Functions.expOfOddOnly(List(1, 2, 3, 4, 5)) shouldBe List(1, 9, 25)
  }

  behavior of "areEqualFor"

// TODO: Odkomentuj testy
//  it should "return true for equal results" in {
//    Functions.areEqualFor(
//      5,
//      x => x * 5,
//      x => x + 20
//    ) shouldBe true
//  }
//
//  it should "return false for not equal results" in {
//    Functions.areEqualFor(
//      5,
//      x => x * 5,
//      x => x * 6
//    ) shouldBe false
//  }
//
//  behavior of "applyAll"
//
//  it should "return input value for zero functions" in {
//    Functions.applyAll(10) shouldBe 10
//  }
//
//  it should "return proper value for single functions" in {
//    Functions.applyAll(
//      10,
//      x => x + 5
//    ) shouldBe 15
//  }
//
//  it should "return proper value for multiple functions" in {
//    Functions.applyAll(
//      10,
//      x => x + 2,
//      x => x * 2,
//      x => x - 10
//    ) shouldBe 14
//  }

}
