/***
  * Extremely brief walktrough of core scala
  */
object Syntax {

  /***
    Defining values in four different ways
    */
  object Definitions {
    //Constant - Immediately evaluated immutable evaluated value
    val x = 100

    //Variable - Immediately evaluated mutable evaluated value
    var x2 = 100

    //no-arg Function - Re-evaluate every time value
    def x3 = 100

    //Lazy evaluated value - Evaluate max once immutable value
    lazy val x4 = 100

    //In a function scope, the last value is returned automatically
    val thisIsAnInt: Int = {
      val x = 2
      val y = 5
      x * y
    }

    //If blocks are expressions
    val number            = 2
    val isGreaterThanFive = if (number > 5) "yes" else "no"

    //Common immutable data structures
    val someList = List(1, 2, 9)
    val someMap  = Map(1 -> "one", 2 -> "two")

    //Pattern matching
    val heyNumber = 9
    val numberString: String = heyNumber match {
      case 0          => "Zero was a bad choice"
      case 9          => "Correct answer"
      case i if i > 9 => "Answer too big!"
      case _          => "Wrong answer"
    }

    /***
      * Loops
      *
      * In pure functional programing we do not have constructs such as a for-loop or a while-loop since they
      * are inherently mutating state
      *
      * Instead all while and for-loops can be implemented as recursions and traversals
      */
    val list1 = List(1, 2, 3)
    val list2 = List(9, 8, 7)

    //Change all elements to strings
    var stringListMutable = List.empty[String]
    for (s <- list1) {
      stringListMutable.appended(s.toString)
    }
    val stringListImmutable = list1.map(_.toString)

    //For comprehensions (calls functions named map and flatMap on container, eg List)
    val resultList = for {
      a <- list1
      b <- list2
    } yield a + b
    //List(10, 9, 8, 11, 10, 9, 12, 11, 10)
  }

  object Functions {

    // Functions are values!
    def singleFunction(i: Int): String = i.toString
    val x1: Int => String              = singleFunction

    //Functions can have larger scopes
    def singleFunctionWithScope(i: Int): String = {
      val twiceValue = i * 2
      twiceValue.toString
    }
    val x2: Int => String = singleFunctionWithScope

    //Multiple arguments to a function
    def multiargFunctionWithScope(i: Int, j: Int): String = {
      val multiplied = i * j
      multiplied.toString
    }
    val x3: (Int, Int) => String = multiargFunctionWithScope

    //Multiple parameter lists to a function, called curried functions after Haskell Curry
    def curriedFunction(i: Int)(j: Int): String = {
      val multiplied = i * j
      multiplied.toString
    }
    val x4: Int => Int => String = curriedFunction

    // Curried functions can be partially applied (specialization)
    val doubler: Int => String = curriedFunction(2)
    val x5: String             = doubler(8) // 16
    val x6: String             = doubler(10) // 20
  }

  /***
    Classes and objects
    */
  object Objects {

    //Standard class (known from java)
    class FooClass(x: Int, y: Int)
    val foo = new FooClass(1, 2)

    /***
    Case class gives us:
      1. A default constructor
      2. All parameters are public
      3. A correct hash function
      4. An equals method that checks equality correctly
      5. Copy functionality
      */
    case class BarClass(x: Int, y: Int)
    val anInstance      = BarClass(1, 2)
    val anotherInstance = BarClass(x = 1, y = 2)
    val copiedX         = anotherInstance.copy(y = 8)

    //Companion objects is the place we put our static methods, eg methods that does not require an instance and constructors
    object BarClass {
      val defaultY = 100
      //apply is a magic word for "constructor"
      def apply(x: Int): BarClass = BarClass(x, defaultY)
    }
    //Using auxiliary constructor with default Y value
    val thirdInstance = BarClass(9)
    val yy            = thirdInstance.y //100

    // By extending AnyVal we can remove boxing from single arg classes. This will be observed as an int at runtime.
    case class RequestCounter(x: Int) extends AnyVal

  }

  /***
    * Traits
    */
  object Traits {
    /*
      Traits are interfaces on steroids.
     */
    trait Bar {
      def x: Int
      def y: Int
      def z: Int = x * y
    }

    trait Foo {
      def dostuff: Int
    }

    //Note the multiple extension of traits
    case class FooBar(x: Int, y: Int) extends Foo with Bar {
      override def dostuff: Int = x + y + z
    }
  }

  /***
    Domains
    */
  object Domains {

    /*
      In purely typed programming we often attempt to constraint the domain of our functions
      Constraining the domain gives actually gives us more power due to the code being easier to reason about
     */

    //What does this function do?
    def someFunctionYouHaveNotMade(i: Int): Int = ???

    //How many implementations of these functions are possible?
    //[] brackets introduces locally named types
    //These functions should work for any A(or B) no matter if they are strings, ints, classes, etc!
    def function1[A](a: A): A = ???

    def function2[A](a: A): (A, A) = ???

    def function3[A](a: A, b: A): (A, A) = ???

    def function4[A, B](a: A, b: B): (A, B) = ???

    /***
      * Algebraic data types
      * Sum & Product
      */
    //Sum types are types where the domain consists of ONE of a set of values
    //Examples: Enums
    //Boolean has two members, true and false

    //Product types are types where the domain consists of a products of members
    //Example: Classes
    case class FooBar(foo: Boolean, bar: Boolean)
    /*
      Boolean is a sum type with 2 members.
      FooBar is a product of these two and such as 2*2=4 members
      (true, true), (true, false), (false, true), (false, false)
     */

    /**
      * Scala type hierarchy
      */
    //Nothing - A type with no members: There is no possible values
    //Unit - A type with a single member, Unit.
    val x: Unit = ()
    //Any - A supertype of all other types
    //Very few functions take Any, and signs of Any in code is often a code smell
    def foo(f: Any): Any = ???

    //So for a given class Foo we have Nothing <: Foo <: Any (<: == is a subtype of)
    //This means we can actually pretend to be python and stuff things in a list, but we lose ALL type safety
    val pythonesqueList: List[Any] = List(1, "asd", false)

    //What is the type of println?
    println

  }
}
