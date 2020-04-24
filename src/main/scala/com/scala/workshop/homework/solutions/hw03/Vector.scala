package com.scala.workshop.homework.solutions.hw03

import org.scalatest.FunSpec

/*
Stwórz klasę reprezentującą wektor (długość, zwrot) dla uproszczenia przyjmijmy, że kierunek wektora jest stały (przestrzeń 1D). Dla klasy stwórz metody pozwalające na:
- dodawanie wektorów
- odejmowanie wektorów
- porównywanie wektorów
- (opcjonalnie) narysowanie wektora na konsoli w formie "o--->" lub "<---o" gdzie "-" odpowiadają długości wektora, "o" wskazuje jego początek, a ">" lub "<" jego koniec i zwrot
 */

class Vector(val initLength: Int, val initDirection: Int) {
  def this() = this(1, 1)
  def this(v:Int) = this(v, v.signum)

  val length: Int = initLength.abs
  val direction: Int = initDirection.signum
  val value: Int = length * direction

  def unit: Vector = new Vector(1, direction)

  def +(v: Vector): Vector = new Vector(value + v.value, (value + v.value).signum)

  def -(v: Vector): Vector = this + v

  def ==(v: Vector): Boolean = value == v.value

  override def toString: String = s"(l: $length, d: $direction)"
}

class VectorTests extends FunSpec {

  describe("Vector") {
    it("should be instantiated with two parameters") {
      assert(new Vector(1, 1) != AnyRef)
    }

    it("should have length property") {
      val len: Int = 3
      val vector = new Vector(len, 1)
      assert(vector.length == len)
    }

    it("should have direction property") {
      val dir: Int = 3
      val vector = new Vector(1, dir)
      assert(vector.direction == 1)
    }

    it("should convert negative length to positive") {
      val len: Int = -3
      val vector = new Vector(len, 1)
      assert(vector.length == len.abs)
    }

    it("should have value property") {
      val dir: Int = -3
      val vector = new Vector(1, dir)
      assert(vector.value == -1)
    }

    it("should have unit property") {
      val dir: Int = -3
      val vector = new Vector(4, dir)
      assert(vector.unit == new Vector(1, -1))
    }

    it("should have string representation as value") {
      val vector = new Vector(-3, -3)
      assert(vector.toString == "(l: 3, d: -1)")
    }

    it("should return zero vector for sum positive and negative vectors") {
      val posVector = new Vector(1, 2)
      val negVector = new Vector(1, -2)
      assert(posVector + negVector == new Vector(0, 0))
    }

    it("should return zero vector for subtraction of identical pos and negative vectors") {
      val posVector = new Vector(1, 2)
      val negVector = new Vector(1, -2)
      assert(posVector - negVector == new Vector(0, 0))
    }

    it("should return vector for sum two positive vectors") {
      val posVector = new Vector(1, 2)
      val negVector = new Vector(1, 2)
      assert(posVector + negVector == new Vector(2, 1))
    }

    it("should return positive vector for adding mixed parameters") {
      val posVector = new Vector(-2, 2)
      val negVector = new Vector(-1, -4)
      assert(posVector + negVector == new Vector(1, 1))
    }

    it("should return negative vector for adding mixed parameters") {
      val posVector = new Vector(1, 2)
      val negVector = new Vector(-2, -2)
      assert(posVector + negVector == new Vector(1, -1))
    }

    it("should return positive vector for subtracting mixed parameters") {
      val posVector = new Vector(-2, 2)
      val negVector = new Vector(-1, -4)
      assert(posVector - negVector == new Vector(1, 1))
    }

    it("should return negative vector for subtracting mixed parameters") {
      val posVector = new Vector(1, 2)
      val negVector = new Vector(-2, -2)
      assert(negVector - posVector == new Vector(1, -1))
    }

    it("should create positive unit vector for non parameter constructor") {
      assert(new Vector() == new Vector(1, 1))
    }

    it("should create positive vector for one parameter constructor") {
      assert(new Vector(2) == new Vector(2, 1))
    }
    it("should create negative vector for one parameter constructor") {
      assert(new Vector(-2) == new Vector(2, -1))
    }
  }
}