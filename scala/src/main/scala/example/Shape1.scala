package example

case class Rect(w : Double, h : Double)
case class Circle(r : Double)

object Geometry {
  // PROBLEM: Adding support for new shape requires access to THIS source file
  def area(r : Rect) = r.w * r.h
  def area(c : Circle) = Math.PI * c.r * c.r
}

// Imagine some other source:
// Geometry.area(someTriangle); // error!

// OK, how about inheritance???
trait Shape {
  def area : Double
}

case class SRect(w : Double, h : Double) extends Shape {
  def area = w * h
}

case class SCircle(r : Double) extends Shape {
  def area = Math.PI * r * r
}

// All great and wonderful, but now define "perimiter"! Ooops...need to change Shape!
