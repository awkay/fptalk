package example

import java.io.File

import example.MySchema.people
import org.scalatest.{BeforeAndAfterAll, FlatSpec, MustMatchers}
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}

class SampleSpec extends FlatSpec with BeforeAndAfterAll with MustMatchers {
  behavior of "Queries"

  it should "be able to query with a where clause" in {
    Console.println("A********************************************************************************")
    transaction {
      from(people)(p =>
        where(p.name === "tony")
          select p).headOption
    }
  }

  it should "honor Option context to include/exclude optional predicates" in {
    Console.println("B********************************************************************************")
    val optionalName: Option[String] = Some("Liz")
    val optionalAge: Option[Int] = Some(42)
    val optionalLikesCarrots: Option[Boolean] = Some(false)
    val specialClause = false

    transaction {
      from(people)(p =>
        where(
          p.name === optionalName.? and
            p.likeCarrots === optionalLikesCarrots.? and
            p.age > optionalAge.?)
          select p).headOption
    }
  }

  override def beforeAll(): Unit = {
    Setup()
  }

}

object Setup {
  Class.forName("org.h2.Driver")

  def apply() = {
    val directory = new File("/tmp")
    for (f <- directory.listFiles() if f.getName.startsWith("test.db"))
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
}
