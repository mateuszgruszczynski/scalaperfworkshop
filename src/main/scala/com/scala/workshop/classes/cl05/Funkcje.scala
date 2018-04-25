package com.scala.workshop

import scala.util.Random

object Funkcje extends App{

  // Funkcja to taki byt który przekształca jedeną wartość w drugą

  val triple: Function1[Int, Int] = x => x * 3

  //  wartość wejściowa ^ | ^ wartość zwracana

  // Alternatywnie można jej typ zdefiniować poprzez notację ze strzałką

  val quadruple: Int => Int = x => x * 4

  // A także z pominięciem typu - wtedy należy określić typ wejściowych danych

  val quintuple = (x: Int) => x * 5

  // Może operować na wielu wartościach wejściowych
  // FunctionN <- N oznacza ilość parametrów wejściowych

  val multiplyN: Function2[Int, Int, Int] = (x, n) => x*n

  // W celu zwracania wielu wartości trzeba je zebrać w tuple

  val tripleBoth: Function2[Int, Int, Tuple2[Int, Int]] = (x, y) => (x*3, y*3)

  // W rzeczywistości wartości wejściowe też są zbierane w tuple,
  // co jest łatwe do zauważenia przy notacji ze strzałką

  val sumAndProductOfFour: (Int, Int, Int, Int) => (Int, Int) = {
    (a, b, c, d) => (a+b+c+d, a*b*c*d)
  }

  // ---------------------------------------------------------

  // Do wywołania funkcji służy metoda apply(...) wbudowana w każdy
  // obkett typu Function

  println(multiplyN.apply(2, 5))

  // ---------------------------------------------------------

  // Metody w rzeczywistości też są rzutowane do funkcji
  // a ich wywołanie rzutowane jest do wywołania metody apply

  def multiply5Met(i: Int) : Int = i * 5
  val multiply5Fun: Int => Int = i => i * 5

  println(multiply5Met(5))
  println(multiply5Fun.apply(10))

  // Metodę można przekształcić w funkcję wywołując ją w ciele funkcji
  def sumAB(a: Int, b: Int) = a+b

  val sumABFun = (a: Int, b: Int) => sumAB(a, b)

  println(sumABFun.apply(2, 3))

  // lub używają tzw. eta expansion
  val sumABEta = sumAB _

  println(sumABEta.apply(2, 3))

  // Funkcje można łączyć (komponować)

  val addB: String => String = s => s + "B"
  val addC: String => String = s => s + "C"
  val addD: String => String = s => s + "D"

  // Z użyciem andThen
  // f, g => f(g(x))
  val addBCD = addB.andThen(addC).andThen(addD)

  println(addBCD.apply("test"))

  // Lub z użyciem compose
  // f, g => g(f(x))
  val addDBC = addB.compose(addC).compose(addD)

  println(addDBC.apply("test"))

  // Funkcje operujące na wielu zmiennych wejściwych
  // trzeba przekształcić do operującej na tuplu by móc
  // taką funkcję komponowac

  val complex: (Int, Int, Int) => Int = (a,b,c) => a+b+c

  val complexAndComplx = complex.tupled.andThen(multiply5Fun)

  // Definiując funkcję mozna użyć notacli case po to by zdefiniować
  // rózne zachowanie dla różnych przedziałów dziedziny funkcji

  val absolute: Int => Int = {
    case i: Int if i >= 0 => i
    case i: Int           => -1 * i
  }

  // Możliwe jest zdefiniowanie funkcji która nie jest zdefiniowana
  // dla całego zakresu danych wejściowych

  val divideBy: (Double, Double) => Double = {
    case (x: Double, y: Double) if y != 0 => x/y // tylko dla y != 0
  }

  // Lepszym rozwiązaniem na definiowanie takiej funkcji jest jednak
  // użycie typu Partial function

  val divideByPartial: PartialFunction[(Double, Double), Double] = {
    case (x: Double, y: Double) if y != 0 => x/y
  }

  val returnError: PartialFunction[(Double, Double), String] = {
    case _ => "Cannot divide by zero"
  }

  // Ma on dodatkową metodę do komponowanie

  val divideBySafe = divideByPartial.orElse(returnError)

  println(divideBySafe.apply((10, 5)))
  println(divideBySafe.apply((10, 0)))

  // Każda funkcję która przyjmuje więcej niż jeden parametr można
  // podzielić na szereg funkcji przyjmujących jeden parametr
  // jest to tzw. Currying

  val curriedMultiply = multiplyN.curried

  // Podzielona tak funkcja wymaga wywołanie wielu metod apply

  println(curriedMultiply.apply(5).apply(6))

  // Dzięki temu możliwe jest definiowanie tak zwanych
  // funkcji partially applied

  val multiplyBy10 = curriedMultiply.apply(10)
  val multiplyBy12 = curriedMultiply.apply(12)

  println(multiplyBy10.apply(4))
  println(multiplyBy12.apply(4))

  // Partially applied functions można też definiować z użyciem
  // placeholdera _ wraz z jego typem

  val multiplyBy20 = multiplyN(20, _: Int)

  println(multiplyBy20.apply(4))

  // Funkcje można oczywiście przekazywać jako parametr do
  // metody czy innej funkcji

  def apply1to10ThenPrint(f: Int => Int): Unit ={
    for(i <- 1 to 10){
      println(f.apply(i))
    }
  }

  apply1to10ThenPrint(multiplyBy10)
  apply1to10ThenPrint(multiplyBy12)
  apply1to10ThenPrint(multiplyBy20)

  // Poza funkcjami definiowanymi wraz z nazwą możemy definiować
  // funkcje anonimowe - tak zwane lambda expressions w skrócie lambdy
  // Funkcje tego typu mogą być przekazane jako parametry wszędzie tam
  // gdzie wymagany jest parametr typu funkcyjnego

  apply1to10ThenPrint(x => {x * x})
  apply1to10ThenPrint(x => { if (x>=5) 5 else x})

  // Lambda musi oczywiście pasować typem do wymagań

  def takeComplex(f: (Int, Int) => String) = {
    println(f.apply(1, 2))
  }

  // Z funkcjami wiąże się również sposób przekazywania zmiennych jako
  // funkcji tzw. call by name, przeciwieństwo standardowego podejścia którym jest
  // call by value
  // Tradycyjnie metoda przyjmuje wartość zmiennej, a więc wartośc ta ustalana jest
  // Na etapie wywołania metody

  def print10times(x: Int) = {
    for(i <- 1 to 10){
      println(x)
    }
  }

  // scala.util.Random.nextInt(n) - zwraca losowy Int z zakrsu 0-n

  print10times(Random.nextInt(100))

  // Jeśli przedefiniujemy metodę tak by brała zmienną jako funkcję => Int
  // wtedy możemy przekazać random nie jako wartość, ale jako funkcję
  // dzięki czemu jego wartość będzie rozwiązaywana na bieżąco przy
  // wykonaniu metody

  def print10timesByName(f: => Int) = {
    for(i <- 1 to 10){
      println(f)
    }
  }

  // Dzięki temu przekazanie do metody wartości nadal będzie traktowane jak
  // przekazanie wartości, lecz przekazanie jej wywołania innej metody będzie
  // traktowane jak przekazanie funkcji na której nastepnie automatycznie
  // wywoływana jest metoda apply

  print10timesByName(Random.nextInt(100))
  print10timesByName(100)
}
