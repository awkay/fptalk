package example

import org.squeryl.{Schema, KeyedEntity}

case class Person(val id : Long, val name : String, val age: Int, val likeCarrots : Boolean) extends KeyedEntity[Long]

object MySchema extends Schema {
  val people = table[Person]
}
