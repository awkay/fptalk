package example

import java.io.File

import example.MySchema.people
import org.scalatest.{BeforeAndAfterEach, FlatSpec, MustMatchers}
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}

class SampleSpec extends FlatSpec with BeforeAndAfterEach with MustMatchers {
  Class.forName("org.h2.Driver")

  override def beforeEach() : Unit = {
    val directory = new File("/tmp")
    for(f <- directory.listFiles() if f.getName.startsWith("test.db"))
      f.delete()
    SessionFactory.concreteFactory = Some(() => {
      val session = Session.create(
        java.sql.DriverManager.getConnection("jdbc:h2:/tmp/test.db"),
        new H2Adapter)
      session.setLogger(Console.println)
      session
    })
    transaction {
      example.MySchema.create
    }
  }

  behavior of "Samples"

  it should "do things" in {
    transaction {
      from(people)(p =>
        where(p.name === "tony")
          select p).headOption
    }
  }

  behavior of "Other things"

  it should "include optional stuff that is present" in {
    val optionalName : Option[String] = Some("Tony")
    transaction {
      from(people)(p =>
        where(p.name === optionalName.?)
          select p).headOption
    }
  }

  it should "not include optional stuff that is not present" in {
    val optionalName : Option[String] = Some("Liz")
    val optionalAge : Option[Int] = Some(42)

    transaction {
      from(people)(p =>
        where(
          p.name === optionalName.? and
          p.age > optionalAge.?)
          select p).headOption
    }
  }
}
