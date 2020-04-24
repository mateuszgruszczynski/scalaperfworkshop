package com.scala.workshop.homework.solutions.hw03

import org.scalatest.FunSpec

/*
Stwórz klasę reprezentującą kalkulator dla trójek wartości. Każda trójka posiada odpowiednio elementy:
- Int
- Double
- Float

Dla uproszczenia załóżmy, że klasa operuje na Tuple3[Int, Double, Float]. Kalkulator powinien mieć metody pozwalające na:
- dodawanie
- odejmowanie
- dzielenie
- mnożenie
- potęgowanie do pełnych potęg

Załóżmy, że operacje na parach (A, B, C) i (D, E, F) są jednoznaczne z przeprowadzeniem osobno operacji na A i D, B i E oraz C i F, przykładowo:

(A, B, C) * (D, E, F) = (A*D, B*E, C*F)
(A, B, C)^2 = (A^2, B^2, C^2)
 */

class Calc3(val init: (Int, Double, Float)) {
  val (_1, _2, _3) = init

  override def toString: String = s"(${_1}, ${_2}, ${_3})"

  def ==(tri: Calc3): Boolean = _1.equals(tri._1) && _2.equals(tri._2) && _3.equals(tri._3)

  def +(tri: Calc3): Calc3 = new Calc3((_1 + tri._1, _2 + tri._2, _3 + tri._3))

  def -(tri: Calc3): Calc3 = new Calc3((_1 - tri._1, _2 - tri._2, _3 - tri._3))

  def *(tri: Calc3): Calc3 = new Calc3((_1 * tri._1, _2 * tri._2, _3 * tri._3))

  def /(tri: Calc3): Calc3 = new Calc3((_1 / tri._1, _2 / tri._2, _3 / tri._3))

  def **(pow: Int): Calc3 = new Calc3(Math.pow(_1, pow).toInt, Math.pow(_2, pow), Math.pow(_3, pow).toFloat)
}

class Calc3Tests extends FunSpec {

  describe("Calc3") {
    val initTriple: (Int, Double, Float) = (1, 2, 3)
    val zeroTriple: (Int, Double, Float) = (0, 0.0, 0.toFloat)
    val oneTriple: (Int, Double, Float) = (1, 1.0, 1.toFloat)
    val doubleTriple: (Int, Double, Float) = (2, 4.0, 6.toFloat)

    it("should be instantiated with Tuple3[Int, Double, Float] parameters") {
      assert(new Calc3(initTriple) != AnyRef)
    }

    it("should print its values separately") {
      val calc3 = new Calc3(initTriple)
      assert(calc3._1 == 1 && calc3._2 == 2.0 && calc3._3 == 3.0)
    }

    it("should print its values") {
      assert(initTriple.toString == "(1,2.0,3.0)")
      assert(new Calc3(initTriple).toString == "(1, 2.0, 3.0)")
    }

    it("should return triple for adding") {
      val tri = new Calc3(initTriple)
      assert(tri + tri == new Calc3(doubleTriple))
    }

    it("should return zero triple for subtracting the same value") {
      val tri = new Calc3(initTriple)
      assert(tri - tri == new Calc3(zeroTriple))
    }

    it("should return triple for multiply the same value") {
      val tri = new Calc3(initTriple)
      assert(tri * tri == new Calc3((1, 4, 9)))
    }

    it("should return one triple for dividing the same value") {
      val tri = new Calc3(initTriple)
      assert(tri / tri == new Calc3(oneTriple))
    }

    it("should calculate power 2") {
      val tri = new Calc3(initTriple)
      assert(tri ** 2 == new Calc3((1, 4, 9.toFloat)))
    }

    it("should calculate power 5") {
      val tri = new Calc3(initTriple)
      assert(tri ** 5 == new Calc3((1, 32, 243.toFloat)))
    }
  }
}
