package com.scala.workshop.homework.hw05

import org.scalatest.{FlatSpec, Matchers}

object ClientsQueries extends App {

  def returnClientsFrom(list: List[Client], country: String): List[Client] = {
    list.filter(c => c.address.country == country)
  }

  def clientsWithMoreThanNProducts(list: List[Client], count: Int): List[Client] = {
    list.filter(c => c.orders.foldLeft(0){(s, o) => s + o.products.length} >= count)
  }

  def usersWithOrdersToNotTheirsAddress(list: List[Client]): List[Client] = {
    list.filter(c => c.orders.exists(o => o.deliveryAddress != c.address))
  }

  def sortByNumberOfProducts(list: List[Client]): List[Client] = {
    list.sortBy(c => c.orders.foldLeft(0){(s, o) => s + o.products.length})
  }

  def hasClientFromCountryOrderedProduct(list: List[Client], country: String, productPart: String): Boolean= {
    list.exists(c => c.address.country == country && c.orders.exists(o => o.products.exists(p => p.name.contains(productPart))))
  }

  def returnAllDistinctProductNames(list: List[Client]) : List[String] = {
    list.flatMap(c => c.orders.flatMap(o => o.products.map(p => p.name)))
  }

  def splitUsersWhoBoughtAndNotProduct(list: List[Client], productPart: String) : (List[Client], List[Client]) = {
    list.partition(c => c.orders.exists(o => o.products.exists(p => p.name.contains(productPart))))
  }

  def totalWeightOfProductsContaining(list: List[Client], namePart: String): BigDecimal = {
    list.flatMap(c => c.orders.flatMap(o => o.products.filter(p => p.name.contains(namePart)))).foldLeft(BigDecimal(0)){(s, p) => s + p.weight}
  }

  def returnClientStats(list: List[Client]): List[(String, Int, Int)] = {
    list.map(c => {
      (s"${c.firstName} ${c.lastName}", c.orders.length, c.orders.foldLeft(0){(s, o) => s + o.products.length})
    })
  }

}

class TestClientsQueries extends FlatSpec with Matchers {

  val clientsList = ClientsRepository.staticClients

  behavior of "returnClientsFromCountry"

  it should "return proper result for 'UK'" in {
    ClientsQueries.returnClientsFrom(clientsList, "UK").length shouldBe 7
  }

  it should "return proper result for 'France'" in {
    ClientsQueries.returnClientsFrom(clientsList, "France").length shouldBe 5
  }

  it should "return proper result for 'Poland'" in {
    ClientsQueries.returnClientsFrom(clientsList, "Poland").length shouldBe 10
  }

  it should "return proper result for 'Belgium'" in {
    ClientsQueries.returnClientsFrom(clientsList, "Belgium").length shouldBe 0
  }

  behavior of "clientsWithMoreThanNProducts"

  it should "return proper value in case of 20 products" in {
    ClientsQueries.clientsWithMoreThanNProducts(clientsList, 20).length shouldBe 3
  }

  it should "return proper value in case of 10 products" in {
    ClientsQueries.clientsWithMoreThanNProducts(clientsList, 10).length shouldBe 45
  }

  it should "return proper value in case of 5 products" in {
    ClientsQueries.clientsWithMoreThanNProducts(clientsList, 5).length shouldBe 60
  }

  behavior of "usersWithOrdersToNotTheirsAddress"

  it should "return proper amount of resulst" in {
    ClientsQueries.usersWithOrdersToNotTheirsAddress(clientsList).length shouldBe 79
  }

  behavior of "sortByNumberOfProducts"

  it should "return proper order of products" in {
    ClientsQueries.sortByNumberOfProducts(clientsList).head.firstName shouldBe "Kate"
    ClientsQueries.sortByNumberOfProducts(clientsList)(10).firstName shouldBe "George"
    ClientsQueries.sortByNumberOfProducts(clientsList)(50).firstName shouldBe "Frank"
    ClientsQueries.sortByNumberOfProducts(clientsList)(75).firstName shouldBe "Lucas"
  }

  behavior of "hasClientFromCountryOrderedProduct"

  it should "return true for france and boots" in {
    ClientsQueries.hasClientFromCountryOrderedProduct(clientsList, "France", "Boots") shouldBe true
  }

  it should "return true for poland and trousers" in {
    ClientsQueries.hasClientFromCountryOrderedProduct(clientsList, "Poland", "Trousers") shouldBe true
  }

  it should "return true for poland and hoodie" in {
    ClientsQueries.hasClientFromCountryOrderedProduct(clientsList, "Russia", "Hoodie") shouldBe false
  }

  behavior of "returnAllDistinctProductNames"

  it should "return proper amount" in {
    ClientsQueries.returnAllDistinctProductNames(clientsList).length shouldBe 797
  }

  behavior of "splitUsersWhoBoughtAndNotProduct"

  it should "should split clients into correctly sized lists in case of shirt" in {
    val (trueList, falseList) = ClientsQueries.splitUsersWhoBoughtAndNotProduct(clientsList, "Shirt")
    trueList.length shouldBe 39
    falseList.length shouldBe 40
  }

  it should "should split clients into correctly sized lists in case of boots" in {
    val (trueList, falseList) = ClientsQueries.splitUsersWhoBoughtAndNotProduct(clientsList, "Boots")
    trueList.length shouldBe 49
    falseList.length shouldBe 30
  }

  behavior of "totalWeightOfProductsContaining"

  it should "return proper total weight for nike" in {
    ClientsQueries.totalWeightOfProductsContaining(clientsList, "Nike")
  }

  it should "return proper total weight for wrangler" in {
    ClientsQueries.totalWeightOfProductsContaining(clientsList, "Wrangler")
  }

  behavior of "returnClientStats"

  it should "return proper clients stats" in {
    ClientsQueries.returnClientStats(clientsList).find(r => r._1 == "George Irvin") shouldBe Some(("George Irvin", 7, 15))
    ClientsQueries.returnClientStats(clientsList).find(r => r._1 == "Bill Jameson") shouldBe Some(("Bill Jameson", 6, 12))
    ClientsQueries.returnClientStats(clientsList).find(r => r._1 == "Alicia Brown") shouldBe Some(("Alicia Brown", 4, 8))
  }
}