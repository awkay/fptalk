package example

object TypeSample {
  trait FoodContainer[T] {
    def firstItem: T
  }

  class Basket[T](val items: Array[T]) extends FoodContainer[T] {
    override def firstItem = items(0)
  }

  trait Food
  trait Fruit extends Food
  case class Apple() extends Fruit
  case class Berry() extends Fruit
  case class Meat() extends Food

  val basket = new Basket(Array( Apple(), Berry() ))

  val item = basket.firstItem

  def eatFruit(container : FoodContainer[Fruit]) = {
    val item = container.firstItem
    Console.println("Yum " + item)
  }

  //eatFruit(basket) // turns out this one is easy to fix, but look at the source for List
}
