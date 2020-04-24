package com.scala.workshop

import scala.collection.mutable
import scala.util.Random

object Kolekcje_Z_Komantarzem extends App{

  // Dwa typy kolekcji - mutable i immutable
  // Do mutable można dodawać i odejmować elementy
  // Immutable nie można edytowac, jedynie kopiować

  val immutableList: List[Int] = List(1, 2, 3)
  val mutableList: mutable.MutableList[Int] = mutable.MutableList(1, 2, 3)

  //immutableList += 4 // brak metody do dodawania
  mutableList += 4

  println(immutableList)
  println(mutableList)

  // Trzy interesujące nas kolekcje to Set, Seq/Listy i Mapy, jest ich znacznie więcej
  // lecz dziedziczą po wspomnianych

  // Set - w skrócie są to listy bez powtórzeń

  val set1 = Set("a", "b", "c")

  println(set1)

  val set2 = set1 + "d"

  println(set2)

  val set3 = set2 - "a"

  println(set3)

  // Dodawanie duplikatu do setu nie spowoduje zduplikowania wartości

  val set4 = set3 + "d"

  // Analogicznie dodawanie 2 setów pododuje pominięcie duplikatów

  val set5 = set2 ++ set3

  println(set5)

  //--------------------------------------------------
  // Listy lub Seq możemy definiować na kilka sposobów
  //--------------------------------------------------

  val lista1: List[String] = List("a", "b", "c", "d")

  // bez podania typu kompilator zgadnie ten typ, jeśli będzie ich wiele
  // to zastosuje Any

  val lista2 = List("x", "y", "z")
  val listaAny = List("a", "b", 2, 4)

  // Listę można równiez złożyć używając notacji :: i Nil (pusta lista)

  val lista3 = "a" :: "b" :: "c" :: Nil

  // Pustą listę można zdefiniować jako

  val emptyList = List.empty

  // lub

  val emptyList2 = Nil

  // z użyciem podobnej notacji mozna łączyć listy

  val lista4 = lista2 ::: lista3

  // mozliwa jest również notacja z ++

  val lista5 = lista2 ++ lista3

  // a także łączyć listy z nowymi elementami

  val lista6 = lista2 :+ "d"

  // również w odwróconej kolejności - wtedy odwraca się też operator +: zamiast :+

  val lista7 = "d" +: lista1

  // listę można uznać za sumę 2 fragmentów: head i tail

  val head :: tail = lista7

  println(head) // <--- pierwszy element listy
  println(tail) // <--- pozostała część listy

  // czy też rozłożyć ją na więcej fragmentów

  val first :: second :: third :: rest = lista7

  println(first)
  println(second)
  println(third)
  println(rest)



  // Sety i Seq(Listy) mają szereg przydatnych metod operujących na lambdach:

  // filtrowanie

  lista1.filter(s => s.length >= 2)

  lista1.filterNot(s => s.length >= 2)

  // wyszukiwanie

  lista1.find(s => s.length >= 2)

  lista1.exists(s => s.length >= 2)

  // sortowanie

  lista1.sorted

  lista1.sortBy(s => s.length)

  lista1.sortWith((s1, s2) => s1.length >= s2.length)

  lista1.reverse // <-- nie sortuje

  // "edytowanie"

  lista1.distinct // <- zwraca listę pomijaąc powtórzenia

  lista1.drop(10) // <- zwraca listę bez 10 pierwszych elementów

  lista1.dropWhile(s => s.length >= 2) // <- pomija pierwsze elementy listy tak długo aż trafi na taki który jest mniejszy niż 2

  // łączyć z innymi listami w listy tupli

  val zippedList = lista1.zip(lista2) // <- tworzy listę tupli dla tych indexów które występują w obu listach

  val zippedAllList =  lista1.zipAll(lista2, "missing1", "missing2") // <- tworzy listę tupli dla wszystkich elementów, jeśli któraś lista jest krótsza dopełnia ją wartościami z parametrów

  val zipIndexList = lista1.zipWithIndex // <- łączy listę z listą indexów (0 - n gdzie n to długość listy)

  // iterowanie

  lista1.foreach(s => println(s.length)) // <- wykonuje lambdę na każdym elemencie listy, nic nie zwraca

  lista1.map(s => s.length) // <- wykonuje lambdę na każdym elemencie listy, zwraca listę wyników

  // redukowanie

  val intList = List(2, 5, 11)

  println(intList.fold(1){ // <- redukuje listę do pojedynczej wartości używając przekazanej lambdy (patrz zdjęcie z tablicy)
    (a,b) => a - b
  })

  println(intList.foldLeft(1){ // <- nowsza implementacja folda
    (a,b) => a - b
  })

  println(intList.foldRight(1){ // <- odwrotna implementacja foldLeft (patrz zdjęcie)
    (a,b) => a - b
  })

  // scan działa dokładnie jak fold, z tym, że zwraca nie jedną wartość a listę z wynikami każdego kolejnego przejścia

  println(intList.scanLeft(1)(_ - _))
  println(intList.scanRight(1)(_ - _))

  println(List(1, 2, 3, 4).product) // <- zwraca iloczyn wartości w tablicy

  println(List(1, 2, 3, 4).sum) // <- zwraca sumę wartości w tablicy

  println(List(1, 2, 3, 4).reduce((x,y) => x + y)) // redukuje listę do pojedynczej wartości z użyciem przekzanej lambdy, zwracany typ musi równać się typowi elementów z listy

  // Listę list możemy przekształcić w listę jednopoziomową z użyciem flatten

  val listOfLists = List(
    List("a", "b", "c", "d"),
    List("x", "y", "z")
  )

  println(listOfLists.flatten)

  // Do mapowania złożonych list można użyć flatMap który jest połączeniem map i flatten

  val l = List(
    List(1, 2, 3, 5),
    List(7, 8, 9, 9)
  )

  println(l.map(l => l.map(x => x*x))) // zwykły ma zwróci listę 2 list z potęgami

  println(l.flatMap(l => l.map(x => x*x))) // flatMap zwróci jedną listę z potęgami

  // List API posiadają również obiekt List który posiada metody do generowania list

  val generatedList = List.fill(10){ Random.nextInt(100) }

  val generatedList2 = List.tabulate(10){ i => i}

  // ------------------------------------------------
  // Mapy równiez możemy definiować na kilka sposobów
  // ------------------------------------------------

  val map1: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)

  val map2: Map[String, Int] = Map(("one", 1), ("two", 2), ("three", 3))

  // Dodawanie do mapy

  val map3 = map1 + ("four" -> 4)

  val map4 = map1 + ("four" -> 4, "five" -> 5)

  // Odejmowanie z mapy wymaga jedynie klucza/y

  val map5 = map1 - ("first")

  val map6 = map1 - ("first", "second")

  // Dodawanie i odejmowanie map

  val map7 = map1 ++ map2

  // Odejmując mapy trzeba przekazać zestaw kluczy mapy, dostępny po wywołaniu .keys

  val map8 = map5 -- map4.keys

  // Mapy mogą być traktowane jak listy tupli i oferują niemal identyczne API jak zwykłe listy

}
