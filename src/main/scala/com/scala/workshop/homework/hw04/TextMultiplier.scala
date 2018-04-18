package com.scala.workshop.homework.hw04

import org.scalatest.{FlatSpec, Matchers}
  
class TextMultiplier(separator: String) {
  def multiplyText(text: String, counter: Int): String = ??? // Dokończ
}

class UppercaseTextMultiplier // Dokończ

trait Borders // Dokończ

class MultiplierTest extends FlatSpec with Matchers{ // << Odkomentuj testy
//
//  val multiplier = new TextMultiplier(", ")
//
//  "regular multiplier" should "return joined text" in {
//    multiplier.multiplyText("lorem", 3) shouldBe "lorem, lorem, lorem"
//  }
//
//  val uppercaseMultiplier = new UppercaseTextMultiplier(", ")
//
//  "uppercase multiplier" should "return joined uppercase text" in {
//    uppercaseMultiplier.multiplyText("lorem", 3) shouldBe "LOREM, LOREM, LOREM"
//  }
//
//  val borderedMultiplier = new TextMultiplier(", ") with Borders
//
//  "multiplier with borders" should "return joined text with borders" in {
//    borderedMultiplier.multiplyText("lorem", 3) shouldBe "<lorem, lorem, lorem>"
//  }
//
//  val borderedUppercaseMultiplier = new UppercaseTextMultiplier(", ") with Borders
//
//  "uppercase multiplier with borders" should "return joined uppercase text with borders" in {
//    borderedUppercaseMultiplier.multiplyText("lorem", 3) shouldBe "<LOREM, LOREM, LOREM>"
//  }
}