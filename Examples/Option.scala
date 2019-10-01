/*
* Pattern matching on lists
* */

val myList = List(1, 2, 42)

val trippleSum  = myList match {
  case firstElement :: secElement :: thirdElement :: Nil => firstElement+secElement+thirdElement
  case _ => 0
}


/*
Option is a ADT!

* */

trait MyOption[A]

case class MySome[A](a: A) extends MyOption[A]
case object MyNone extends MyOption[Nothing]



/*
Going from Option[A] to Option[B] via function A=>B
use Option.map(function)
 */

val stringOption: Option[String] = Option("23")
val longOption: Option[Long] = stringOption.map(s => s.toLong)s