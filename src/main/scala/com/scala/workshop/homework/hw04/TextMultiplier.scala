package com.scala.workshop.homework.hw04

import org.scalatest.{FlatSpec, Matchers}
  
// Stwórz klasę 'TextMultiplier' przyjmującą string jako parametr
class TextMultiplier(separator: String) {
  
  // Metoda powinna brać dwa parametry: tekst i ilość powtórzeń i zwrócić powtórzony n-razy tekst
  // Kolejne wystąpienia tekstu powinny być odzielone separatorem który podany jest jako parametr konstruktora klasy
  def multiplyText(text: String, counter: Int): String = (for (i <- 1 to counter) yield {
    text
  }).mkString(separator)
}

// Dodaj klasę UppercaseTextMultiplier rozszerzającą TextMultiplier
// metoda multiplyText nowej klasy powinna działać jak ta w TextMultiplier
// tylko zwracać tekst dużymi literami (patrz metoda toUppercase()) 

class UppercaseTextMultiplier(separator: String) extends TextMultiplier(separator) {

  override def multiplyText(text: String, counter: Int) = super.multiplyText(text, counter).toUpperCase

}

// Dodaj trait Borders który można zastosować zarówno na TextMultiplier jak i UppercaseTextMultiplier
// Powinien on modyfikować oryginalną metodę multiplyText w taki sposób, że dodaje do niej "<" i ">" na początku i końcu tekstu

trait Borders extends TextMultiplier{

  override def multiplyText(text: String, counter: Int) = "<" + super.multiplyText(text, counter) + ">"

}

class MultiplierTest extends FlatSpec with Matchers{

  val multiplier = new TextMultiplier(", ")

  "regular multiplier" should "return joined text" in {
    multiplier.multiplyText("lorem", 3) shouldBe "lorem, lorem, lorem"
  }

  val uppercaseMultiplier = new UppercaseTextMultiplier(", ")

  "uppercase multiplier" should "return joined uppercase text" in {
    uppercaseMultiplier.multiplyText("lorem", 3) shouldBe "LOREM, LOREM, LOREM"
  }

  val borderedMultiplier = new TextMultiplier(", ") with Borders

  "multiplier with borders" should "return joined text with borders" in {
    borderedMultiplier.multiplyText("lorem", 3) shouldBe "<lorem, lorem, lorem>"
  }

  val borderedUppercaseMultiplier = new UppercaseTextMultiplier(", ") with Borders

  "uppercase multiplier with borders" should "return joined uppercase text with borders" in {
    borderedUppercaseMultiplier.multiplyText("lorem", 3) shouldBe "<LOREM, LOREM, LOREM>"
  }
}