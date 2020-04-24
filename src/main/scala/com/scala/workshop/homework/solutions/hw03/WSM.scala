package com.scala.workshop.homework.solutions.hw03

import org.scalatest.FunSpec

/*
Stwórz klasę i metody pozwalające na wypisanie rozpisanego wzoru skróconego mnożenia (dla zadanych danych wejściowych) na konsolę w formie np.:
"(1 + 4)^2 = 1^2 + 2 * 1 * 4 + 4^2 = 1 + 8 + 16 = 25"
Klasa powinna posiadać metody do obsługi wzorów ze strony: http://matematyka.pisz.pl/strona/55.html
Dla uproszczenia przyjmijmy, że operujemy jedynie na Int-ach.
*/

class WSM(a: Int, b: Int) {
  val value: Int = a * a + 2 * a * b + b * b
  val Phases: (String, String, String, String) = Tuple4(
    s"($a + $b)^2",
    s"$a^2 + 2 * $a * $b + $b^2",
    s"${a * a} + ${2 * a * b} + ${b * b}",
    s"$value"
  )

  override def toString: String = s"${Phases._1} = ${Phases._2} = ${Phases._3} = ${Phases._4}"
}

object WSM_Simple {
  //(a+b)^2
  def a_p_b_p2(a: Int, b: Int): String = {
    val value: Int = Math.pow(a + b, 2).toInt
    s"($a + $b)^2 = $a^2 + 2 * $a * $b + $b^2 = ${a * a} + ${2 * a * b} + ${b * b} = $value"
  }

  //(a-b)^2
  def a_m_b_p2(a: Int, b: Int): String = {
    val value: Int = Math.pow(a - b, 2).toInt
    s"($a - $b)^2 = $a^2 - 2 * $a * $b + $b^2 = ${a * a} - ${2 * a * b} + ${b * b} = $value"
  }

  //(a-b)(a+b)
  def a_p_b_x_a_m_b(a: Int, b: Int): String = {
    val value: Int = a * a - b * b
    s"($a + $b) * ($a - $b) = $a^2 - $b^2 = ${a * a} - ${b * b} = $value"
  }

  //(a+b)^3
  def a_p_b_p3(a: Int, b: Int): String = {
    val value: Int = Math.pow(a + b, 3).toInt
    s"($a + $b)^3 = $a^3 + 3 * $a^2 * $b + 3 * $a * $b^2 - $b^3 = " +
      s"${a * a * a} + ${3 * a * a * b} + ${a * a * a} + ${3 * a * b * b} + ${b * b * b} = $value"
  }

  //(a-b)^3
  def a_m_b_p3(a: Int, b: Int): String = {
    val value: Int = Math.pow(a - b, 3).toInt
    s"($a - $b)^3 = $a^3 - 3 * $a^2 * $b + 3 * $a * $b^2 - $b^3 = " +
      s"${a * a * a} - ${3 * a * a * b} + ${a * a * a} + ${3 * a * b * b} - ${b * b + 3} = $value"
  }

  //a^3+b^3
  def a_p3_p_b_p3(a: Int, b: Int): String = {
    val value: Int = (Math.pow(a, 3) + Math.pow(b, 3)).toInt
    s"$a^3 + $b^3 = ($a + $b) * ($a^2 - $a * $b + $b^2) = ${a + b} * (${a * a} - ${a * b} + ${b * b}) = " +
      s"${a + b} * ${a * a - a * b + b * b} = $value"
  }

  //a^3-b^3
  def a_p3_m_b_p3(a: Int, b: Int): String = {
    val value: Int = (Math.pow(a, 3) - Math.pow(b, 3)).toInt
    s"$a^3 - $b^3 = ($a - $b) * ($a^2 + $a * $b + $b^2) = ${a - b} * (${a * a} + ${a * b} + ${b * b}) = " +
      s"${a - b} * ${a * a + a * b + b * b} = $value"
  }
}

class WSMTests extends FunSpec {

  describe("WSM") {
    it("should be instantiated with two parameters") {
      assert(new WSM(1, 1) != AnyRef)
    }

    it("should have proper return value for zeros") {
      assert(new WSM(0, 0).value == 0)
    }

    it("should have proper return value") {
      assert(new WSM(1, 4).value == 25)
    }

    it("should have proper return value as string") {
      val outString:String = "(1 + 4)^2 = 1^2 + 2 * 1 * 4 + 4^2 = 1 + 8 + 16 = 25"
      assert(new WSM(1, 4).toString == outString)
    }

  }
}