package com.scala.workshop.homework.solutions.hw05

import org.scalatest.{FlatSpec, Matchers}

/*
Klasa “RegularSolid” implementuje bryłę foremną i przyjmuje dwa parametry:
- a - długość boku
- fv - funkcję liczącą objętość bryły

Zdefiniuj 3 klasy dziedziczące po “RegularSolid” tak by wszystkie 3 przyjmowały jedynie długość boku, a funkcja licząca
objętość powinna być przekazana na poziomie dziedziczenia.

- Tetahedron - czworobok foremny
- Cube - sześcian foremny
- Octahedron - ośmiościan foremny

Wzory na objętość znajdziecie: http://www.megamatma.pl/uczniowie/wzory/stereometria-wzory/wielosciany

Do obliczeń możecie wspomóc się biblioteką scala.math
 */

class RegularSolid(a: Double, fv: Double => Double){
  def volume = fv.apply(a)
}

case class Tetahedron(a: Double) extends RegularSolid(a, a => a*a*a*math.sqrt(2)/12)

case class Cube(a: Double) extends RegularSolid(a, a => a*a*a)

case class Octahedron(a: Double) extends RegularSolid(a, a => a*a*a*math.sqrt(2)/3)

class TestSolids extends FlatSpec with Matchers {

  behavior of "Tetahedron"

  it should "properly calculate volume for a = 7" in {
    new Tetahedron(7).volume shouldBe 40.42 +- 0.01
  }

  it should "properly calculate volume for a = 25" in {
    new Tetahedron(25).volume shouldBe 1841.42 +- 0.01
  }

  behavior of "Cube"

  it should "properly calculate volume for a = 7" in {
    Cube(7).volume shouldBe 343.0
  }

  it should "properly calculate volume for a = 25" in {
    Cube(25).volume shouldBe 15625.0
  }

  behavior of "Octahedron"

  it should "properly calculate volume for a = 7" in {
    Octahedron(7).volume shouldBe 161.69 +- 0.01
  }

  it should "properly calculate volume for a = 25" in {
    Octahedron(25).volume shouldBe 7365.69 +- 0.01
  }

}
