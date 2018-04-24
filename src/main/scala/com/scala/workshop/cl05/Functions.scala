package com.scala.workshop.cl05

object Functions extends App{

  val fun1: Function0[String] = () => "test"

  val sum2: Function2[Int, Int, Int] = (x, y) => x + y

  val sum2b: (Int, Int) => (Int) = (x, y) => x + y

  val sum3c = (x: Int, y: Int) => x + y

  val sumAndProduct : (Int, Int) => Tuple2[Int, Int] = (x, y) => (x+y, x*y)

  //println(sum2.apply(2, 5))

  def sum3(x: Int, y: Int, z: Int) = x + y + z

  //val sum3f: (Int, Int, Int) => (Int) = (x, y, z) => sum3(x, y, z)

  //val sum3eta = sum3 _

  //println(sum3eta.apply(2, 5, 9))



  //callFunction((x, y, z) => sum3(x, y, z))

  //callFunction(sum3 _)

  val addA: String => String = s => s + "A"
  val addB: String => String = s => s + "B"
  val addC: String => String = s => s + "C"

  val addABC = addA.andThen(addB).andThen(addC) // ===>

  //println(addABC.apply("test"))

  val addCBA = addA.compose(addB).compose(addC) // <===

  //println(addCBA.apply("test"))

  val divideBy: (Double, Double) => Double = {
    case (x, by) if by != 0 => x/by
  }

  val divideByPart: PartialFunction[Tuple2[Double, Double], Double] = {
    case (x, by) if by != 0 => x/by
  }

  val returnZero: PartialFunction[Tuple2[Double, Double], Double] = {
    case (x, by) => 0.0
  }

  //println(divideByPart.orElse(returnZero).apply((10.0, 2.5)))
  //(divide/ByPart.orElse(returnZero).apply((10.0, 0.0)))

  val suma3 = sum3 _

  val suma3rozbite = suma3.curried

  suma3.apply(1, 2, 3)

  suma3rozbite.apply(1).apply(2).apply(3)

  val dodaj10i20 = suma3rozbite.apply(10).apply(20)

  //println(dodaj10i20.apply(1))

  def callFunction(f: (Int, Int, Int) => Int) = {
    println(f.apply(10, 10, 10))
  }

//  callFunction((x, y, z) => x + y + z)
//
//  callFunction((x, y, z) => x - y - z)
//
//  callFunction((x, y, z) => {
//    (x + 10) * (y + z)
//  })

  def printNTimes(i: Int) = {
    for(n <- 1 to 10){
      println(i)
    }
  }

  printNTimes(scala.util.Random.nextInt(1000))

  def printNTimesByName(i: => Int) = {
    for(n <- 1 to 10){
      println(i)
    }
  }

  printNTimesByName(scala.util.Random.nextInt(1000))
  printNTimesByName(2222)

}
