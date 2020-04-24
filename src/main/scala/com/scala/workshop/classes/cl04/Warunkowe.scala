package com.scala.workshop.classes.cl04

import java.io.Serializable

object Warunkowe extends App{

  // If ; else if ; else jest podstawową instrukcją warunkową,
  // ale ze wzlędu na ograniczone możliwości i przydługawą implementację
  // jest znacznie rzadziej używany niż match case

    def czyTo1lub2_IF(i: Int) = {
      if(i == 1){
        println("TAK")
        println("1")
      } else if(i == 2){
        println("TAK")
        println("2")
      } else {
        println("NIE")
      }
    }

    czyTo1lub2_IF(2)
    czyTo1lub2_IF(1)
    czyTo1lub2_IF(12)

  // Match case jest znacznie bardziej popularny, gdyż jego implementacja jest
  // znacznie bardziej zwięzła, a jest znacznie bardziej wszechstronny
  // domyślne zachowanie określanie jest przez matchowanie do "_"
  def czyTo1lub2(i: Int) = {
    i match {
      case 1 => println("TAK 1")
      case 2 => println("TAK 2")
      case _ => println("NIE")
    }
  }

  czyTo1lub2(2)
  czyTo1lub2(1)
  czyTo1lub2(12)

  // Można matchować do konkretnych wartości
  def czyImieDamskieCzyMeskie(imie: String) = {
    imie.endsWith("a") match {
      case true => println("Damskie")
      case false => println("Meskie")
    }
  }

  czyImieDamskieCzyMeskie("Adam")
  czyImieDamskieCzyMeskie("Joanna")

  // Można matchować do jednej z wielu wartości
  def kolorOwocu(owoc: String) = {
    owoc match {
      case "pomarańcza" | "mandarynka" => println("pomarańczowy")
      case "cytryna" => println("żółty")
      case _ => println("nie wiem")
    }
  }

  // Można matchować do wartości z warunkiem
  def czyNumerPozytywny(i: Int) = {
    i match {
      case i if i >= 0 => "pozytywny"
      case _ => "negatywny"
    }
  }

  // Można matchować po złożonych typach
  // i rozkładać te typy na zmienne (tu tuple t rozłożone na x, y, z)
  def czyTupleRowne(t: Tuple3[Int, Int, Int]) = {
    t match {
      case (x, y, z) if x == y && y == x => x+y+z
      case _ => false
    }
  }

  // Mozna używać placeholdera "_" by oznaczać te wartości które mogą
  // przyjmowac dowolną wartość
  def czyTupleMaTrue(t: Tuple4[Boolean, Int, Int, Boolean]) = {
    t match {
      case (true, _, _, true) => "oba true"
      case (true, _, _, _) => "pierwsze true"
      case (_, _, _, true) => "ostatni true"
      case _ => "żaden true"
    }
  }

  // Można matchować nie tylko po wartości ale i po typie
  // Matching uda się też poporawnie jeśli badana klasa
  // dziedziczy po określonym typie czy trait-cie

  class C1 extends Serializable

  def jakiToTyp(a: Any) = {
    a match {
      case s: Serializable => println("Serializable")
      case i: Int => "Integer"
      case f: Float => "Float"
      case p: Pojazd => p.iloscKol
      case _ => println("coś innego")
    }
  }

  jakiToTyp(new C1)
  jakiToTyp(new Jednoslad("", ""))

  // Chcąc matchować po wielu wartościach można zebrać je w tuple
  // z następnie ten tuple rozłożyć z powrotem
  def dodaj2zmienne(a: Any, b: Any) = {
    (a, b) match {
      case (a:Int, b:Int) => a + b
      case (a: String, b: String) => a + b
      case (a: Pojazd, b: Pojazd) => throw new Exception("Nie można dodawać pojazdów")
      case _ => "costam innego"
    }
  }

  // Z użyciem match można rozkładać także opcje
  def dodajObaJesliSa(a: Option[Int], b: Option[Int]) = {
    (a, b) match {
      case (Some(x), Some(y)) => x + y
      case (Some(x), None) => "b is none"
      case (None, Some(y)) => "a is none"
      case _ => "both are none"
    }
  }

  // Można matchować po case clasach z placeholderem
  def czyJestRowerem(p: Pojazd) = {
    p match {
      case Pojazd(_, "rower", _, _) => "rower"
      case _ => "nie rower"
    }
  }

  // Czy też rozkładać case clasę na zmienne
  def czyJestRowerem2(p: Pojazd) = {
    p match {
      case Pojazd(n, t, _, _) if t == "rower" => s"$n jest rowerem"
      case Pojazd(n, t, _, _) if t == "quad" => s"$n jest quadem"
      case _ => "nie rower"
    }
  }

  // Wynik matcha można przypisać do zmiennej
  def czyJestRowerem3(p: Pojazd) = {
    val result = p match {
      case Pojazd(n, t, _, _) if t == "rower" => s"$n jest rowerem"
      case Pojazd(n, t, _, _) if t == "quad" => s"$n jest quadem"
      case _ => "nie rower"
    }
    println(result)
  }

  println(czyJestRowerem2(Pojazd("quad", "quad", 4, Silnik(500, 10, ""))))
  println(czyJestRowerem2(Pojazd("gazela", "rower", 4, Silnik(500, 10, ""))))

  // Używając match i for można ławto generować duże ilości unikatowych danych
  // na podsatwie predefiniowanych list

  case class Person(imie: String, nazwisko: String, plec: String)

  val imiona = List("Adam", "Ewa", "Piotr")
  val nazwiska = List("Nowak", "Kowalski", "Malinowski")

  def generatePersons(l1: List[String], l2: List[String]) = {
    for(imie <- l1; nazwisko <- l2) yield {
      imie.endsWith("a") match {
        case true => Person(imie, nazwisko, "kobieta")
        case false => Person(imie, nazwisko, "meżczyzna")
      }
    }
  }

  println(generatePersons(imiona, nazwiska).mkString("\n"))
}
