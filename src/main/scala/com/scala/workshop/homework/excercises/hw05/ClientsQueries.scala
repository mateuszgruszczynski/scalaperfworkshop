package com.scala.workshop.homework.excercises.hw05

import org.scalatest.{FlatSpec, Matchers}

/*
W pliku: src/main/scala/com/scala/workshop/homework/excercises/hw05/ClientsRepository.scala znajduje się obiekt “ClientsRepository”
posiadający w sobie listę klientów. Każdy klient ma m.in. listę zamówień w których każdy posiada listę produktów.
Klasy Client, Address, Order, Product znajdziesz w tym samym pliku.

Kilka uwag: Do wartości zmiennoprzecinkowych używany jest BigDecimal, nie ma on automatycznej konwersji z wartości typu 2.2
więc trzeba go definiować jako BigDecimal(2.2). Przy używaniu notacji z * (do zmiennej liczby parametrów) konieczne może
okazać się umieszczenie złożonych typów np: funkcji w nawiasach () inaczej kompilator nie będzie poprawnie rozumiał o co nam chodzi.
Np: (A => B) * zamiast A => B *


I zdefiniuj w metody (każda z nich powinna przyjmować listę klientów jako pierwszy parametr):

returnClientsFrom - powinna zwracać listę klientów z kraju podanego jako drugi parametr

clientsWithMoreThanNProducts - powinna zwracać listę klientów którzy zamówili więcej niż N produktów gdzie N jest
                               podawane jako drugi parametr metody

usersWithOrdersToNotTheirsAddress - powinna zwracać listę klientów którzy choć raz zamówili coś na adres inny niż swój

sortByNumberOfProducts - powinna zwracać listę klientów posortowanych po ilości zakupionych produktów (rosnąco)

hasClientFromCountryOrderedProduct - powinna zwracać true lub false w zależności czy istnieje taki klient z kraju przekazanego
                                     jako drugi parametr który zamówił produkt którego część nazwy przekazano jako trzeci parametr

returnAllDistinctProductNames - powinna zwrócić listę stringów zawierającą wszystkie zakupione przez klientów produkty ale bez powtórzeń

splitUsersWhoBoughtAndNotProduct - powinna podzielić listę klientów na dwie listy - tych którzy kupili produkt którego część nazwy
                                   została przekazana jako drugi parametr i tych którzy takiego produktu nie kupili

totalWeightOfProductsContaining - powinna zwracać sumę wagi wszystkich zakupionych produktów które zawierają w swojej nazwie
                                  fragment przekazany jako drugi parametr metody

returnClientStats - powinna zwracać statystyki klienta w postaci tupla zawierającego:
                    - imie i nazwisko
                    - całkowitą liczbę jego zamówień
                    - całkowitą liczbę jego produktów

 */

object ClientsQueries extends App {

  def returnClientsFrom(list: List[Client], country: String): List[Client] = ???

  def clientsWithMoreThanNProducts(list: List[Client], count: Int): List[Client] = ???

  def usersWithOrdersToNotTheirsAddress(list: List[Client]): List[Client] = ???

  def sortByNumberOfProducts(list: List[Client]): List[Client] = ???

  def hasClientFromCountryOrderedProduct(list: List[Client], country: String, productPart: String): Boolean = ???

  def returnAllDistinctProductNames(list: List[Client]) : List[String] = ???

  def splitUsersWhoBoughtAndNotProduct(list: List[Client], productPart: String) : (List[Client], List[Client]) = ???

  def totalWeightOfProductsContaining(list: List[Client], namePart: String): BigDecimal = ???

  def returnClientStats(list: List[Client]): List[(String, Int, Int)] = ???

}

class TestClientsQueries extends FlatSpec with Matchers { // << Odkomentuj testy
//
//  val clientsList = ClientsRepository.staticClients
//
//  behavior of "returnClientsFromCountry"
//
//  it should "return proper result for 'UK'" in {
//    ClientsQueries.returnClientsFrom(clientsList, "UK").length shouldBe 7
//  }
//
//  it should "return proper result for 'France'" in {
//    ClientsQueries.returnClientsFrom(clientsList, "France").length shouldBe 5
//  }
//
//  it should "return proper result for 'Poland'" in {
//    ClientsQueries.returnClientsFrom(clientsList, "Poland").length shouldBe 10
//  }
//
//  it should "return proper result for 'Belgium'" in {
//    ClientsQueries.returnClientsFrom(clientsList, "Belgium").length shouldBe 0
//  }
//
//  behavior of "clientsWithMoreThanNProducts"
//
//  it should "return proper value in case of 20 products" in {
//    ClientsQueries.clientsWithMoreThanNProducts(clientsList, 20).length shouldBe 0
//  }
//
//  it should "return proper value in case of 10 products" in {
//    ClientsQueries.clientsWithMoreThanNProducts(clientsList, 10).length shouldBe 41
//  }
//
//  it should "return proper value in case of 5 products" in {
//    ClientsQueries.clientsWithMoreThanNProducts(clientsList, 5).length shouldBe 57
//  }
//
//  behavior of "usersWithOrdersToNotTheirsAddress"
//
//  it should "return proper amount of resulst" in {
//    ClientsQueries.usersWithOrdersToNotTheirsAddress(clientsList).length shouldBe 79
//  }
//
//  behavior of "sortByNumberOfProducts"
//
//  it should "return proper order of products" in {
//    ClientsQueries.sortByNumberOfProducts(clientsList).head.firstName shouldBe "Kate"
//    ClientsQueries.sortByNumberOfProducts(clientsList)(10).firstName shouldBe "George"
//    ClientsQueries.sortByNumberOfProducts(clientsList)(50).firstName shouldBe "Frank"
//    ClientsQueries.sortByNumberOfProducts(clientsList)(75).firstName shouldBe "Lucas"
//  }
//
//  behavior of "hasClientFromCountryOrderedProduct"
//
//  it should "return true for france and boots" in {
//    ClientsQueries.hasClientFromCountryOrderedProduct(clientsList, "France", "Boots") shouldBe true
//  }
//
//  it should "return true for poland and trousers" in {
//    ClientsQueries.hasClientFromCountryOrderedProduct(clientsList, "Poland", "Trousers") shouldBe true
//  }
//
//  it should "return true for poland and hoodie" in {
//    ClientsQueries.hasClientFromCountryOrderedProduct(clientsList, "Russia", "Hoodie") shouldBe false
//  }
//
//  behavior of "returnAllDistinctProductNames"
//
//  it should "return proper amount" in {
//    ClientsQueries.returnAllDistinctProductNames(clientsList).length shouldBe 108
//  }
//
//  behavior of "splitUsersWhoBoughtAndNotProduct"
//
//  it should "should split clients into correctly sized lists in case of shirt" in {
//    val (trueList, falseList) = ClientsQueries.splitUsersWhoBoughtAndNotProduct(clientsList, "Shirt")
//    trueList.length shouldBe 39
//    falseList.length shouldBe 40
//  }
//
//  it should "should split clients into correctly sized lists in case of boots" in {
//    val (trueList, falseList) = ClientsQueries.splitUsersWhoBoughtAndNotProduct(clientsList, "Boots")
//    trueList.length shouldBe 49
//    falseList.length shouldBe 30
//  }
//
//  behavior of "totalWeightOfProductsContaining"
//
//  it should "return proper total weight for nike" in {
//    ClientsQueries.totalWeightOfProductsContaining(clientsList, "Nike")
//  }
//
//  it should "return proper total weight for wrangler" in {
//    ClientsQueries.totalWeightOfProductsContaining(clientsList, "Wrangler")
//  }
//
//  behavior of "returnClientStats"
//
//  it should "return proper clients stats" in {
//    ClientsQueries.returnClientStats(clientsList).find(r => r._1 == "George Irvin") shouldBe Some(("George Irvin", 7, 15))
//    ClientsQueries.returnClientStats(clientsList).find(r => r._1 == "Alicia Brown") shouldBe Some(("Alicia Brown", 4, 8))
//  }
}