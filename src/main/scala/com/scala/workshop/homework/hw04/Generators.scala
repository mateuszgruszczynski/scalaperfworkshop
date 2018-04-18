package com.scala.workshop.homework.hw04

import org.scalatest.{FlatSpec, Matchers}

object Generators{

  def commonMultiples(numberA: Int, numberB: Int, limit: Int): List[Int] = ??? // Dokończ

  def stringSquare(borderSize: Int): String = ??? // Dokończ

  def polishNames(names: List[String], surnames: List[String]): List[String] = ??? // Dokończ
}

case class Outfit(
  name: String,
  category: String,
  color: String,
  size: String,
  price: Double
){
  def withColor(color: String): Outfit = ??? // Dokończ
  def withSize(size: String): Outfit = ??? // Dokończ
  def generateSimilar(colors: Option[List[String]], sizes: Option[List[String]]): List[Outfit] = ??? // Dokończ
}

class GeneratorsTest extends FlatSpec with Matchers{ // << Odkomentuj testy
//
//  behavior of "commonFactors"
//
//  it should "return proper values for 6 and 7 and limit of 100" in {
//    Generators.commonMultiples(6, 7, 100) should contain only (42, 84)
//  }
//
//  it should "return proper values for 4 and 7 and limit of 1000" in {
//    Generators.commonMultiples(4, 7, 500) should contain only
//      (28, 56, 84, 112, 140, 168, 196, 224, 252, 280, 308, 336, 364, 392, 420, 448, 476)
//  }
//
//  it should "return empty result for 11 and 13 and limit of 100" in {
//    Generators.commonMultiples(11, 13, 100) shouldBe empty
//  }
//
//  behavior of "printSquare"
//
//  it should "return proper value for size of 5" in {
//    Generators.stringSquare(5) shouldBe "xxxxx\nx   x\nx   x\nx   x\nxxxxx"
//  }
//
//  it should "return proper value for size of 2" in {
//    Generators.stringSquare(2) shouldBe "xx\nxx"
//  }
//
//  it should "return proper value for size of 1" in {
//    Generators.stringSquare(1) shouldBe "x"
//  }
//
//  "polishNames" should "generate proper list on names" in {
//    val names = List("Adam", "Beata", "Karol")
//    val surnames = List("Kowalski", "Malinowska", "Nowak")
//    Generators.polishNames(names, surnames) should contain only (
//      "Pan Adam Kowalski",
//      "Pan Adam Malinowski",
//      "Pan Adam Nowak",
//      "Pani Beata Kowalska",
//      "Pani Beata Malinowska",
//      "Pani Beata Nowak",
//      "Pan Karol Kowalski",
//      "Pan Karol Malinowski",
//      "Pan Karol Nowak"
//    )
//  }
//
//  behavior of "Outfit"
//
//  val testOutfit = Outfit("tshirt", "tops", "green", "XL", 45.00)
//
//  it should "generate new outfit with new color" in {
//    testOutfit.withColor("red") shouldBe Outfit("tshirt", "tops", "red", "XL", 45.00)
//  }
//
//  it should "generate new outfit with new size" in {
//    testOutfit.withSize("S") shouldBe Outfit("tshirt", "tops", "green", "S", 45.00)
//  }
//
//  it should "generate similar outfits with different colors" in {
//    val colors = List("blue", "orange", "yellow", "red")
//    testOutfit.generateSimilar(Some(colors), None) should contain only (
//      Outfit("tshirt", "tops", "blue", "XL", 45.00),
//      Outfit("tshirt", "tops", "orange", "XL", 45.00),
//      Outfit("tshirt", "tops", "yellow", "XL", 45.00),
//      Outfit("tshirt", "tops", "red", "XL", 45.00)
//    )
//  }
//
//  it should "generate similar outfits with different sizes" in {
//    val sizes = List("S", "M", "L", "XXL")
//    testOutfit.generateSimilar(None, Some(sizes)) should contain only (
//      Outfit("tshirt", "tops", "green", "S", 45.00),
//      Outfit("tshirt", "tops", "green", "M", 45.00),
//      Outfit("tshirt", "tops", "green", "L", 45.00),
//      Outfit("tshirt", "tops", "green", "XXL", 45.00)
//    )
//  }
//
//  it should "generate similar outfits with different colors and sizes" in {
//    val colors = List("blue", "orange")
//    val sizes = List("S", "M")
//    testOutfit.generateSimilar(Some(colors), Some(sizes)) should contain only (
//      Outfit("tshirt", "tops", "blue", "S", 45.00),
//      Outfit("tshirt", "tops", "blue", "M", 45.00),
//      Outfit("tshirt", "tops", "orange", "S", 45.00),
//      Outfit("tshirt", "tops", "orange", "M", 45.00)
//    )
//  }
}
