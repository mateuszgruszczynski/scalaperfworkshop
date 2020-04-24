package com.scala.workshop.homework.solutions.hw04

import org.scalatest.{FlatSpec, Matchers}

/*
Do klasy Person dopisz metodę “isAdult” która zwraca true lub false w zależności od tego czy osoba jest pełnoletnia, dla
uproszczenia można przyjąć, że zawsze jest 16 kwietnia 2018, a pełnoletność uzyskuje się w dzień urodzin o północy
! dla chętnych:  można odczytać aktualną datę za pomocą którejś z Javowych bibliotek

Do klasy Student dodaj metodę “isStudentOfUniversityOrSubject” która przyjmuje jako parametry nazwę uniwersytetu i kierunek i zwraca String:
- “Both” - jeśli oba pola się zgadzają z danymi studenta
- “University” - jeśli jedynie uniwersytet się zgadza
- “Subject” - jeśli tylko kierunek się zgadza
- “None” - jeśli żaden z pól się nie zgadza

Do klasy “Employee” dopisz metodę “salaryLowerEqualHigher” która przyjmuje jako parametr kwotę (Int) i zwraca tekst "Equal",
"Lower" albo "Higher" w zależności od tego czy pensja pracownika jest równa, niższa lub wyższa od podanej kwoty

Do klasy “Employee” dodaj metodę “hasManagingPosition” która zwraca true jeśli nazwa pozycji pracownika zawiera:
- "Manager" lub "Director"
- "Chief" i "Officer" np. Chief Technology Officer
- skrót: "CTO", "CEO", "COO", "KAM", "CSM" lub "HRM"
W przeciwnym wypadku metoda powinna zwracać false
 */

case class BirthDate(year: Int, month: Int, day: Int)

abstract class Person(val name: String, val surname: String, val birthDate: BirthDate){
  def isAdult: Boolean = birthDate match {
    case BirthDate(y, _, _) if y < 2000 => true
    case BirthDate(y, m, _) if y <= 2000 && m < 4 => true
    case BirthDate(y, m, d) if y <= 2000 && m <= 4 && d <= 16 => true
    case _ => false
  }
}

class Student(name: String, surname: String, birthDate: BirthDate, val university: String, val subject: String, val year: Int) extends Person(name, surname, birthDate){
  def isStudentOfUniversityOrSubject(university: String, subject: String) = (university, subject) match {
    case (this.university, this.subject) => "Both"
    case (this.university, _) => "University"
    case (_, this.subject) => "Subject"
    case _ => "None"
  }
}

class Employee(name: String, surname: String, birthDate: BirthDate, val company: String, val position: String, val salary: Int) extends Person(name, surname, birthDate){

  def salaryLowerEqualHigher(amount: Int) = salary match {
    case i: Int if i == amount => "Equal"
    case i: Int if i < amount => "Lower"
    case i: Int if i > amount => "Higher"
  }

  def hasManagingPosition = this.position match {
    case p: String if p.contains("Manager") => true
    case p: String if p.contains("Director") => true
    case p: String if p.contains("Officer") && p.contains("Chief") => true
    case "CTO" | "CEO" | "COO" | "KAM" | "CSM" | "HRM" => true
    case _ => false
  }
}

class PersonTest extends FlatSpec with Matchers{

  behavior of "isAdult"

  it should "return true for Student born on 16th april 2000" in {
    new Student("Adam", "Nowak", BirthDate(2000, 4, 16), "AGH", "Elektronika", 1).isAdult shouldBe true
  }

  it should "return true for Student born on 31th december 1998" in {
    new Student("Adam", "Nowak", BirthDate(1998, 12, 31), "AGH", "Elektronika", 1).isAdult shouldBe true
  }

  it should "return false for Student born on 17th april 2000" in {
    new Student("Adam", "Nowak", BirthDate(2000, 4, 17), "AGH", "Elektronika", 1).isAdult shouldBe false
  }

  it should "return true for Employee born on 1st january 2001" in {
    new Employee("Adam", "Nowak", BirthDate(2001, 1, 1), "Google", "Director", 10000).isAdult shouldBe false
  }

  it should "return false for Employee born in 2037" in {
    new Employee("Adam", "Nowak", BirthDate(2037, 1, 1), "Google", "Director", 10000).isAdult shouldBe false
  }

  behavior of "isStudying"

  lazy val testStudent = new Student("Adam", "Nowak", BirthDate(1995, 7, 11), "AGH", "Fizyka", 3)

  it should "return Both in case of matching university and subject" in {
    testStudent.isStudentOfUniversityOrSubject("AGH", "Fizyka") shouldBe "Both"
  }

  it should "return Subject in case of invalid university" in {
    testStudent.isStudentOfUniversityOrSubject("UJ", "Fizyka") shouldBe "Subject"
  }

  it should "return University in case of invalid subject" in {
    testStudent.isStudentOfUniversityOrSubject("AGH", "Matematyka") shouldBe "University"
  }

  it should "return None in case of invalid university and subject" in {
    testStudent.isStudentOfUniversityOrSubject("UJ", "Matematyka") shouldBe "None"
  }

  behavior of "salaryLessEqualMore"

  lazy val testEmployee = new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Lawyer", 5000)

  it should "return 'Lower' in case of salary lower than provided amount" in {
    testEmployee.salaryLowerEqualHigher(5001) shouldBe "Lower"
  }

  it should "return 'Equal' in case of salary equal to provided amount" in {
    testEmployee.salaryLowerEqualHigher(5000) shouldBe "Equal"
  }

  it should "return 'Higher' in case of salary higher than provided amount" in {
    testEmployee.salaryLowerEqualHigher(4999) shouldBe "Higher"
  }

  behavior of "hasManagingPosition"

  it should "return true in case of Financial Manager" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Financial Manager", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return true in case of Financial Director" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Financial Director", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return true in case of Manager" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Manager", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return true in case of Director" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Director", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return true in case of Chief Accounting Officer" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Chief Accounting Manager", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return true in case of CTO" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "CTO", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return true in case of HRM" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "HRM", 5000)
      .hasManagingPosition shouldBe true
  }

  it should "return false in case of Lawyer" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Lawyer", 5000)
      .hasManagingPosition shouldBe false
  }

  it should "return false in case of Security Officer" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Security Officer", 5000)
      .hasManagingPosition shouldBe false
  }

  it should "return false in case of Chief Cook" in {
    new Employee("Adam", "Nowak", BirthDate(1990, 11, 11), "Amazon", "Chief cook", 5000)
      .hasManagingPosition shouldBe false
  }

}