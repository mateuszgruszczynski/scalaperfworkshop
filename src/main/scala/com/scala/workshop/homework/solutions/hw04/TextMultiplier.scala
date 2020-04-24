package com.scala.workshop.homework.solutions.hw04

import org.scalatest.{FlatSpec, Matchers}

/*
Dokończ klasę “TextMultiplier” która przyjmuje separator (String) jako parametr domyślnego konstruktora.

W klasie zdefiniuj metodę “multiplyText” która przyjmuje tekst i ilość jego powtórzeń (String i Int) a następnie zwraca
powtórzony konkretny raz tekst (String). Każde kolejne powtórzenie powinno być oddzielone separatorem zdefiniowanym przy
konstrukcji obiektu klasy.

Np. “ab”, 3 → “ab,ab,ab”

Następnie zdefiniuj klasę “UppercaseTextMultiplier” która będzie dziedziczyć po “TextMultiplier”. Metoda “multiplyText”
tej klasy powinna zwracać dokładnie ten sam wynik co w klasie “TextMultiplier” ale w postaci dużych liter.

Np. “ab”, 3 → “AB,AB,AB”

Następnie dodaj trait “Borders” który można zastosować zarówno na “TextMultiplier” jak i “UppercaseTextMultiplier”.
Powinien on modyfikować oryginalną metodę “multiplyText” w taki sposób, że dodaje do jej wyniku "<" na początku i ">" na końcu.

Np. “ab”, 3 → “<ab,ab,ab>”

 */

class TextMultiplier(separator: String) {
  def multiplyText(text: String, counter: Int): String = (for (i <- 1 to counter) yield {
    text
  }).mkString(separator)

  //Wersja alternatywna metody
  def multiplyTextAlternative(text: String, counter: Int): String = Seq.fill(counter)(text).mkString(separator)
}

class UppercaseTextMultiplier(separator: String) extends TextMultiplier(separator) {
  override def multiplyText(text: String, counter: Int) = super.multiplyText(text, counter).toUpperCase
}

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