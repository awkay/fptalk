package example

// like a namespace
object Geometry {
  // Attempt 1 ********************************************************************************
  trait Shape
  case class Rect(w: Double, h: Double) extends Shape
  case class Circle(r: Double) extends Shape
  // OK, I can add new cases at any time (e.g. Triangle)

  def perimiter(s : Shape) = s match {
    case Rect(w, h) => 2*w + 2*h
    case Circle(r) => 2* Math.PI * r
    case _ => throw new IllegalArgumentException("Unsupported Shape")
  }

  // OR new functionality can be added as well at any time
  def area(s: Shape) = s match {
    case Rect(w, h) => w * h
    case Circle(r) => Math.PI * r * r
    // But extending both to support new ones requires changing the source of the functions
    case _ => throw new IllegalArgumentException("Unsupported Shape")
  }

  // Attempt 2 ********************************************************************************
  // I could try using overloading on arguments...
  def area2(r: Rect) = r.w * r.h
  def area2(c: Circle) = Math.PI * c.r * c.r

  // New shape with area support: Just write new code. Excellent!
  case class Square(s: Double) extends Shape
  def area2(s: Square) = s.s * s.s

  // But if I want to use polymorphism...
  var someShape: Shape = Circle(3.5)

  // I'm stuck...compiler cannot resolve, nor can I write a general purpose function
  //area2(someShape)
}

// Attempt 3 ********************************************************************************
// OK, how about inheritance???
trait Shape2 {
  def area: Double
}

case class SRect(w: Double, h: Double) extends Shape2 {
  def area = w * h
}

case class SCircle(r: Double) extends Shape2 {
  def area = Math.PI * r * r
}

// All great and wonderful, but now define "perimiter"! Ooops...need to change all of the original
// code! I cannot add new functions over the existing types.
