package com.scala.workshop.cl05

import scala.util.Random

object Collections extends App{

  val s1: Set[Int] = Set(1, 2, 3, 4, 5, 6)

  val s2 = Set(4, 5, 6, 7, 8, 9)

  println(s1 ++ s2)

  println(s1 + 4)

  s1 -- s2

  s1 - 2

  //---------

  val sek1: Seq[Int] = Seq(1, 2, 3, 4, 5, 6, 7)

  val sek2 = Seq(1, 2, 3, 4)

  println(sek1 ++ sek2)

  println(sek1 ++: sek2)

  val list1 = 1 :: 2 :: Nil

  println(list1 :+ 3)

  println(3 +: list1)

  val emptyList = Nil

  val emptyList2 = List.empty

  val intList = List(1, 2, 3, 4, 5, 6)

  val head :: tail = intList

  println(head)
  println(tail)

  val first :: second :: third :: fourth :: rest = intList

  val randomInts: List[Int] = List.fill(100){Random.nextInt(10000)}

  println(randomInts)

  val intsList = List.tabulate(100)(i => 2*i*i)

  println(intsList)

  println(intsList.filter(i => i % 100 == 0))
  println(intsList.filterNot(i => i % 100 == 0))

  println(intsList.find(i => i % 9999 == 0 && i > 0))

  println(intsList.exists(i => i % 9999 == 0 && i > 0))

  val l = List(32432, 343, 232, 22, 2, 6, 112, 445, 3233)

  println(l.sorted)

  println(l.sorted.reverse)

  val l2 = List("aaa", "a", "aaaaaa", "aa")

  //println(l2.sortWith((x, y) => x.length < y.length))

  //println(l2.sortBy(s => s.length))

  //println(l.drop(4))

  //println(l.dropWhile(i => i > 30))

  //println(l.take(4))

  //println(l.takeWhile(i => i > 30))

  val l3 = List(1, 2, 4, 5, 2, 1, 2, 3, 2)

  //println(l3.distinct)

  //println(l3.slice(2, 4))

  //println(l3.splitAt(4))

  //println(l3.groupBy(i => i % 2 == 0))

  //println(l3.partition(i => i % 2 == 0))

  val l01 = List(1, 2, 3, 4, 5, 6, 7, 8)

  val l02 = List("b", "d", "f", "d")

  //println(l01.zip(l02))

  //println(l01.zipAll(l02, 0, " "))

  //println(l02.zipWithIndex)

  l01.foreach(i => {
    println(s"Element listy l01: $i")
  })

  println(l01.map(i => i*i*i))

  val lll = List(2, 5, 11)

  println(lll.fold(0){(x, y) => x + y})
  println(lll.foldLeft(0){(x, y) => x + y})
  println(lll.foldRight(0){(x, y) => x + y})

  println(lll.scan(0){(x, y) => x + y})
  println(lll.scanLeft(0){(x, y) => x + y})
  println(lll.scanRight(0){(x, y) => x + y})

  println(lll.sum)
  println(lll.product)

  println(lll.reduce((x, y) => x*x + y*y))

  val listaList = List(
    List(1, 3, 5, 6, 7),
    List(5, 6, 7, 2, 5, 1, 6),
    List(7, 4, 8, 1, 3, 4)
  )

  println(listaList.flatten)

  // -----

  val map1: Map[String, Int] = Map("a" -> 2, "b" -> 3, "c" -> 5)

  val map2 = Map(("a", 4), ("b", 7), ("c", 9))

  val map3 = map2 + ("d"-> 5)

  val map4 = map2 - "b"

  val map5 = map1 ++ map2

  println(map5)

  map2.keys

  map3.foreach(t => {
    println(s"${t._1} ----> ${t._2}")
  })

  map3.foreach({
    case (k: String, v: Int) => println(s"${k} ----> ${v}")
  })

}
