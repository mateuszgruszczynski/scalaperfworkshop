package com.scala.workshop.homework.solutions.hw03

import org.scalatest.FunSpec

/*
Zdefiniuj klasę reprezentującą drink (napój). Powinna uwzględniać takie parametry jak:
- nazwę
- zawartość alkoholu
- pojemność
- cenę
- legalność

Dla klasy zdefiniuj metody pozwalające na:
- sprawdzenie czy dany drink jest bezalkoholowy
- nalanie drinka - czyli wygenerowanie nowej instancji klasy ale z zadaną pojemnością (większą lub mniejszą niż oryginalna)
- wymieszanie drinka z innym - w wyniku powinna powstać nowa instancja klasy o nazwie przekazanej jako parametr metody
                               i o właściwościach będących sumą/średnią bazowych instancji, załóż, że drink zawierający
                               w sobie nielegalny składnik również jest nielegalny
- wygenerowanie etykietki - wielopoziomowego stringa, zawierającego dane o drinku, załóżmy, że pojemność wyrażana jest
                            w [ml], cena w [zł] a zawartość alkoholu w [%]
 */

class Beverage(_name: String, _volumeInMl: Int, _pricePerMl: Double, _percent: Double = 0, _isLegal: Boolean = true) {

  def name: String = _name

  def volumeInMl: Int = _volumeInMl

  def pricePerMl: Double = _pricePerMl

  def percent: Double = _percent

  def isAlcoholFree: Boolean = percent <= 1

  def alcoholInfo: String = if (isAlcoholFree) f"free ($percent%-5.2f%% vol)" else f"$percent%-5.1f%% vol"

  def isLegal: Boolean = _isLegal

  def legalInfo: String = if (_isLegal) "full legal" else "- not legal?"

  def badge: String = {
    f"""Beverage: $name $legalInfo
       | volume: $volumeInMl ml
       | alcohol: $alcoholInfo
       | unit price: $pricePerMl%-4.3f zł/ml
       | total price: ${_pricePerMl * _volumeInMl}%-6.2f zł
     """.stripMargin
  }

  override def toString: String = badge

  def pour(newVolumeInMl: Int): Beverage = new Beverage(_name, newVolumeInMl, _pricePerMl, _percent, _isLegal)

  //jak dla krotek stosowaś  metody w rodzaju forEach, map, filter, reduce?

  private def _calculateNewUnitPrice(unitPrice: (BigDecimal, BigDecimal),
                                     volumeInMl: (BigDecimal, BigDecimal)): Double =
    ((unitPrice._1 * volumeInMl._1 + unitPrice._2 * volumeInMl._2) / (volumeInMl._1 + volumeInMl._2)).toDouble

  private def _newPercentage(percent: Double*)(volumeInMl: Int*): Double = {
    val alcWaterMap = (percent zip volumeInMl).map(
      item => (item._1 / 100 * item._2, item._2 - (item._1 / 100 * item._2)))
    val alcWaterTuple = alcWaterMap.reduce((x, y) => (x._1 + y._1, x._2 + y._2))
    val alcoholSum = alcWaterTuple._1
    val waterSum = alcWaterTuple._2
    alcoholSum / (alcoholSum + waterSum) * 100
  }

  def +(nb: Beverage): Beverage = new Beverage(
    _name + nb.name,
    _volumeInMl + nb.volumeInMl,
    _calculateNewUnitPrice((_pricePerMl, nb.pricePerMl), (_volumeInMl, nb.volumeInMl)),
    _newPercentage(_percent, nb.percent)(_volumeInMl, nb.volumeInMl),
    _isLegal && nb.isLegal
  )

  def mix(newName: String, toMix: Beverage): Beverage = {
    new Beverage(newName,
      _volumeInMl + toMix.volumeInMl,
      _calculateNewUnitPrice((_pricePerMl, toMix.pricePerMl), (_volumeInMl, toMix.volumeInMl)),
      _newPercentage(_percent, toMix.percent)(_volumeInMl, toMix.volumeInMl),
      _isLegal && toMix.isLegal)
  }
}

class BeverageTests extends FunSpec {
  val eps = 0.00001
  val water = new Beverage("Water", 1000, 0.001d)
  val spirit = new Beverage("Spirit", 1000, 0.1, _percent = 99.9, _isLegal = false)
  val legalPrice = .1D
  val notLegalPrice = .2D
  val legal = new Beverage("Legal drink", 1000, legalPrice, _isLegal = true)
  val notLegal = new Beverage("not legal drink", 1000, notLegalPrice, _isLegal = false)

  describe("Original drink") {

    it("should be instantiated with 3 parameters") {
      assert(new Beverage("aaaa", 1, 1) != AnyRef)
    }

    it("should be instantiated with 5 parameters") {
      assert(new Beverage("aaaa", 1, 1, 1) != AnyRef)
    }

    it("should inform about alcohol free if there is no more than 1% of alc") {
      assert(new Beverage("AlcoholFree", 1000, 0.001d, _percent = 1).isAlcoholFree)
    }

    it("should inform about alcohol if there is more than 1% of alc") {
      assert(!new Beverage("With Alcohol", 1000, 0.001d, _percent = 2).isAlcoholFree)
    }

    it("should inform if is not legal") {
      assert(!new Beverage("not legal", 1000, 0.001d, _isLegal = false).isLegal)
    }

    it("should have a description not legal") {
      val name = "not legal"
      assert(!new Beverage(name, 1000, 0.001d, _isLegal = false).toString.contains("full legal"))
    }
  }

  describe("Pour") {
    it("pour from of not legal  is not legal") {
      assert(!notLegal.pour(500).isLegal)
    }
    it("pour from of legal is legal") {
      assert(!notLegal.pour(500).isLegal)
    }
  }

  describe("Mix") {
    it("mix 100ml of spirit + 10 liters of water is practically alcohol free") {
      assert((spirit.pour(100) + water.pour(10000)).isAlcoholFree)
    }

    it("mix of legal and not legal is not legal") {
      assert(!legal.mix("New", notLegal).isLegal)
    }

    it("should create drink from two not legal which is still not legal") {
      assert(!notLegal.mix("New", notLegal).isLegal)
    }
    it("should calculate price of two mixed drink: the same") {
      assert(legal.pour(500).mix("New", legal.pour(500)).pricePerMl == legalPrice)
    }
    it("should calculate average price of two different drink") {
      println(notLegal.pour(500).mix("New", legal.pour(500)))
      assert(Math.abs(notLegal.pour(500).mix("New", legal.pour(500)).pricePerMl -
        ((notLegalPrice + legalPrice) / 2)) < eps)
    }
    it("should calculate average price of two different drink with different volumes") {
      println(notLegal.pour(900).mix("New", legal.pour(100)))
      assert(notLegal.pour(900).mix("New", legal.pour(100)).pricePerMl ==
        (BigDecimal(notLegalPrice) * 9 + BigDecimal(legalPrice)) / BigDecimal(10)
      )
    }
  }
}