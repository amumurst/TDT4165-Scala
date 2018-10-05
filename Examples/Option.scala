/*
* Pattern matching on lists
* */

val myList = List(1, 2, 42)

val trippleSum  = myList match {
  case firstElement::secElement::thirdElement::Nil => firstElement+secElement+thirdElement
  case _ => 0
}


/*
Option is a ADT!

trait Option[A]

case class Some[A](a: A) extends Option[A]
case object None extends Option[Nothing]
* */




/*
Going from Option[A] to Option[B] via function A=>B
use Option.map(function)
 */

val stringOption: Option[String] = Option("23")
val longOption: Option[Long] = stringOption.map(s => s.toLong)s