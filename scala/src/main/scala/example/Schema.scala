package example

import org.squeryl.{Schema, KeyedEntity}

case class Person(id: Long,
                  name: String,
                  age: Int,
                  likeCarrots: Boolean) extends KeyedEntity[Long]

object MySchema extends Schema {
  val people = table[Person]
}
