object Quicknotes {
  /*
 Defining values, three different ways
   */

  val x  = 100
  var x2 = 100

  def x3 = 100

  lazy val x4 = 100

  /*
  Functions
   */
  object Functions {
    def function1[A](a: A): A = a

    def function2[A](a: A): (A, A) = ???

    def function3[A](a: A, b: A): (A, A) = ???

    def function4[A, B](a: A, b: B): (A, B) = ???
  }

  /*
  Classes
   */
  class FooClass(x: Int, y: Int)

  /*
  Case class gives us:
    1. A default constructor
    2. All parameters are public
    3. A correct hash function
    4. An equals method that checks correctly

  By extending AnyVal we can remove boxing from single arg classes
   */
  case class Bar44(x: Int) extends AnyVal

  case class Foo2Class(x: Int, y: Int)

  object Foo2Class {
    val z = 100
  }

  /*
  Trait
   */

  trait Bar {
    def x: Int

    def y: Int

    def z: Int = x * y
  }

  trait Foo {
    def dostuff(x: Int, y: Int): Int
  }

  case class FooBar(x: Int, y: Int) extends Foo with Bar {
    override def dostuff(x: Int, y: Int): Int = x + y + z
  }

}
