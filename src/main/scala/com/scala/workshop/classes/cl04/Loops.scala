package com.scala.workshop.classes.cl04

import scala.collection.immutable.NumericRange

object Loops extends App{

  // Pętle do while i while do

  var counter = 100

  while(counter <= 10){
    println(counter)
    counter += 1
  }

  counter = 100

  do {
    println(counter)
    counter += 1
  } while (counter <= 10)

  // Pętla for (okreslana często jako "for comprehantion") iteruje po kolekcjach

  // Podstawową kolekcją jest Range który można zdefiniowac na kilka sposobów:

  val r : Range = 1 to 10

  val r2: Range = 1 until 10

  val r3: Range = 1 to 100 by 5

  val r4: Range = Range(1, 100, 5)

  println(Range(1, 11, 1).mkString(", "))

  val r5: Range = 100 to 1 by -1

  val r6: NumericRange[Char] = 'a' to 'z'

  // Podstawowa pętla for wygląda następująco:

  for(i <- 1 to 25){
    println(s"Krok $i")
  }


  // For może iterowac jednocześnie po wielu kolekcjach,
  // będzie wówczas iterował po ich iloczynie kartezjańskim
  for(i <- 1 to 25; j <- 1 to 10){
    println(s"Krok $i, $j")
  }

  // Wewnatrz fora można ustalić warunek który musi być spełniony przez
  // iterowane wartości by ciało pętli zostało dla nich wykonane
  for(i <- 1 to 25; j <- 1 to 10 if i == j*2){
    println(s"Krok $i, $j")
  }

  val l1 = List("A", "B", "C", "C")
  val l2 = List(10, 20, 30, 40, 50)

  // Sam for jedynie iteruje po kolekcji i "zapomina" wynik kolejnych iteracji i zwraca Unit
  // Ale można uzyć słowa kluczowego yield do zebrania wyników wszystkich iteracji
  // w listę (lub inną kolekcję), wynik takiego działania można przypisać więc do zmiennej
  val listaTupli = for(i <- l1; j <- l2) yield {
    (i, j)
  }

  println(listaTupli)

}
