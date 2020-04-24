package com.scala.workshop.homework.solutions.hw05

import org.scalatest.{FlatSpec, Matchers}

/*
Zdefiniuj funkcje:

maxSumProd - powinna pobierać 3 inty i zwracać “sum” lub “prod” w zależności od tego, czy większa jest suma (sum) czy
             iloczyn (prod) przekazanych wartości

sumOfDigits - powinna pobierać integer i zwracać sumę wszystkich cyfr w danej liczbie

distance2D - powinna przyjmować 2 obiekty typu Point2D (zdefiniowany w pliku) które reprezentują punkt w przestrzeni 2D
             i wyliczać odległość pomiędzy tymi dwoma punktami

filterOddOnly - powinna przyjmować listę integerów i zracać tą samą listę ale z pominięciem parzystych wartości

expList - powinna przyjmować listę integerów i zwracać listę ich drugich potęg

expOfOddOnly - powinna przyjmować listę integerów i zwracać listę potęg nieparzystych wartości z listy, zdefiniuj tę
               funkcję używając kompozycji

Zdefiniuj metody:

areEqualFor - powinna przyjmować integer oraz 2 funkcje przekształcające int w int. W wyniku powinna zwracać true lub
              false w zależności czy wynik obu funkcji wykonanych dla przekazanej w pierwszym parametrze wartości jest równy

applyAll - powinna przyjmować integer oraz zmienną liczbę funkcji przekształcających int w int (notacja z *) a w wyniku
           wykonywać kolejno wszystkie przekazane funkcje. Każda kolejna funkcja powinna brać wynik poprzedniej jako swój
           parametr wejściowy, dla pierwszej funkcji parametrem wejściowym powinien być pierwszy parametr przekazany do metody.
 */

case class Point2D(x: Int, y: Int)

object Functions {

  val maxSumProd: (Int, Int, Int) => String = (a , b, c) => {
    a+b+c > a*b*c match {
      case true => "sum"
      case false => "product"
    }
  }

  val sumOfDigits: Int => Int = x => {
    x.toString.map(c => c.toString.toInt).sum
  }

  val distance2D: (Point2D,Point2D) => Double = (p1, p2) => {
    math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y))
  }

  val filterOddOnly: List[Int] => List[Int] = l => l.filter(l => l % 2 == 1) // zostawia tylko te które mają resztę z dzielenia przez 2 = 1

  val expList: List[Int] => List[Int] = l => l.map(v => v * v) // bierze listę i zwraca listę z elementami podniesionymi do kwadratu

  val expOfOddOnly: List[Int] => List[Int] = filterOddOnly.andThen(expList) // woła funkcję filtrującą liste a na jej wyniku funkcję podnoszącą elementy listy do potęgi

  def areEqualFor(input: Int, f1: Int => Int, f2: Int => Int) = {
    f1.apply(input) == f2.apply(input)
  }

  def applyAll(input: Int, fs: (Int => Int) *) = {
    fs.foldLeft(input)((in, f) => f.apply(in)) // używa fold do wywołania funkcji przekazanych jako parametry i przekazywania wyniku tej operacji do kolejnej iteracji
  }

}

class FunctionsTest extends FlatSpec with Matchers {

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

 //TODO: Odkomentuj testy
  it should "return true for equal results" in {
    Functions.areEqualFor(
      5,
      x => x * 5,
      x => x + 20
    ) shouldBe true
  }

  it should "return false for not equal results" in {
    Functions.areEqualFor(
      5,
      x => x * 5,
      x => x * 6
    ) shouldBe false
  }

  behavior of "applyAll"

  it should "return input value for zero functions" in {
    Functions.applyAll(10) shouldBe 10
  }

  it should "return proper value for single functions" in {
    Functions.applyAll(
      10,
      x => x + 5
    ) shouldBe 15
  }

  it should "return proper value for multiple functions" in {
    Functions.applyAll(
      10,
      x => x + 2,
      x => x * 2,
      x => x - 10
    ) shouldBe 14
  }

}
