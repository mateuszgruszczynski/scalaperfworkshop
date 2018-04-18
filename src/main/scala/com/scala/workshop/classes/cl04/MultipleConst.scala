package com.scala.workshop.classes.cl04

class MultipleConst {

  def test(t: List[String]) : Int = 1

  // Przy metodach o tej samej nazwie kompilator zwraca uwagę
  // jedynie na pierwszy poziom typu parametrów więc np. dwie metody
  // które przyjmują listy różnych podtypów będą widziane jako konfliktujące metody
  //def test(t: List[Int]) : Int = 1

  // Można to obejść używając np. używając szerszego typu
  def testAll(t: List[Any]) = t.head.toString

  // Ewentualnie dodać parametr typu
  def testType1[T](t: List[T]) = t.head

  // w tym taki zawężony do wszystkich typów dziedziczących po np. A
  def testType2[T <: A](t: List[T]) = t.head

  // lub zawężony do typów posiadających metodę np. printX (tzw. duck typing)
  def testDuck[T <: {def printX : Unit}](a: T) = a.printX

  // metoda może użwyać wiele parametrów typu
  def takeAreturnB[A, B](a: A) : B = ???
}

object Aaa {
  def printX = println("x")
}

object Bbb {
  def printX = this.finalize()
}

// Dziedziczenie w Scali definiujemy słowem kluczowym extends
class A {
  def printX = println("x")
}

// Jeśli chcemy naspisac metodę lub zmienną klasy-rodzica musimy użyć słowa kluczowego override
class B extends A{
  override def printX = println("B prints X")
}

class C extends A

// Standardowe klasy w scali nie posiadają poprawnych metod:
// - equals
// - hashCode
// Przez co nie da się poprawnie porównywać dwóch instancji tej samej klasy
class Numer(val x: Int)

object Example1 extends App{

  val n1 = new Numer(5)

  val n2 = new Numer(5)

  println(n1 == n2) // zwróci false
}

// Scala posiada jednak coś takiego jak case classy - specjalny typ klas
// dla których m.in. są generowane poprawne metody equals i hashCode
// Wszystkie pola case classy są też automatycznie publiczne

case class CaseNumer(x: Int)

object Example2 extends App{

  val n1 = CaseNumer(5)

  val n2 = CaseNumer(5)

  println(n1 == n2) // zwróci true
}

// Case classy mają specyficzną metodę .copy() która pozwala na tworzenie
// pełnych kopii instancji klasy, lub kopii ze zmienioną częścią pól
case class Pojazd(
  nazwa: String,
  typ: String,
  iloscKol: Int,
  silnik: Silnik
){
  def zSilnikiem(s: Silnik) = this.copy(silnik = s)
  def zNazwa(n: String) = this.copy(nazwa = n)
}

case class Silnik(
  pojemnosc: Int,
  moc: Int,
  typ: String
)

// Instancjonując case klasę nie musimy podawać słowa kluczowego new
object Example3 extends App{
  val samochod = Pojazd(
    "ford focus",
    "samochod",
    4,
    Silnik(
      2000,
      150,
      "diesel"
    )
  )

  val samochod2 = samochod.copy(silnik = Silnik(1600, 120, "benzynowy"))

  // Dzięki temu, że copy zwraca obiekt, mozna łączyć metody kopiujące w coś
  // w formie DSLa (tzw. method chaining / chain building)
  val samochod3 = samochod
    .zNazwa("vw golf")
    .zSilnikiem(Silnik(2500, 250, "benzynowy"))

  // Można tworzyć różnież obiekty klas anonimowych
  val punkt3D = new {
    val x: Int = 10
    val y: Int = 20
    val z: Int = 30

    def print = println(s"$x $y $z")
  }

  punkt3D.print
}

// Klasy abstrakcyjne to klasy których nie można instancjonowac
// muszą być dziedziczone przez inne klasy
abstract class TestowaKlasa(val test: String)

// Po klasy oznaczone jako sealed można dziedziczyć tylko w tym samym
// pliku w którym klasa taka została zdefiniowana
sealed class Jednoslad(val nazwa: String, val typ: String){
  def czyToRower : Boolean = "rower" == typ
  def pojemnoscCalkowita = 0
}

class Rower(nazwa: String) extends Jednoslad(nazwa, "rower"){
  override def czyToRower: Boolean = true
  def oryginalyCzyToRower = {
    println("to przed")
    super.czyToRower
  }
}


// Odpowiednikiem interfejsów z Javy czy C# są traity
// Pozwalają one na rozszerzanie klas, jedna klasa może korzystać z wielu traitów
// Traity nie przyjmują parametrów, stąd żadko używa się ich do rozszerzania o same parametry
// Zwykle dodają nowe metody do klas

// Traity można definiować jako niezalezne twory
trait TraitExample{
  def method(x: Int) = x*x
}

// Lub jako rozszerzenia dla konkretnych klas, wtedy traitu mozna użyć
// jedynie z klasą bazową lub dowolnym jej dzieckiem
trait Bagaznik extends Jednoslad{
  val pojemnoscB = 50
  def czySieZmiesciB(rozmiar: Int) = rozmiar <= pojemnoscB

  override def pojemnoscCalkowita: Int = pojemnoscB + super.pojemnoscCalkowita
}

trait Przyczepka extends Jednoslad{
  val pojemnoscP = 250
  def czySieZmiesciP(rozmiar: Int) = rozmiar <= pojemnoscP

  override def pojemnoscCalkowita: Int = pojemnoscP + super.pojemnoscCalkowita
}

// Do używania traitów służy słowo kluczowe with
class Motocykl(nazwa: String, silnik: Silnik) extends Jednoslad(nazwa, "motocykl") with Bagaznik with Przyczepka {
}

// Aczkolwiek jeśli klasa nie dziedziczy po innej to pierwszy trait
// wymaga użycia słowa kluczowego extends
class ClassExample extends TraitExample

object A extends App{
  val romet = new Rower("romet")

  // Obiekty klasy używającej traitu mają dostęp do jego metod
  val moto = new Motocykl("Suzuki GSR", Silnik(750, 60, "benzynowy"))
  moto.czySieZmiesciB(55)

  romet.czyToRower

  // W przypadku obiektów klas które nie używają danego traitu
  // można uzyć go na jednym konkretnym obiekcie
  val rowerZBagaznikiem = new Rower("Romet gazela") with Bagaznik

  // Taka instancja będzie wtedy miała dostęp do metod traitu
  rowerZBagaznikiem.czySieZmiesciB(45)

  // Mozna tez tworzyć instancje klasy anonimowej z traitem
  trait SimpleTrait{
    def printSimple = println("simple")
  }
  val simple = new SimpleTrait {}

  // Taka instancja klasy anononimowej będzie miała wtedy dostęp jedynie do metod traitu
  simple.printSimple

}