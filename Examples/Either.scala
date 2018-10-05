import scala.util.Try

/*
Either is also an ADT

trait Either[L, R]

case class Left[L](l: L) extends Either[L, Nothing]
case class Right[R](r: R) extends Either[Nothing, R]
 */



/*
Try has both toOption and toEither
* */

val attempt: Try[Int] = Try(45)
val resultOption: Option[Int] = attempt.toOption
val resultEither: Either[Throwable, Int] = attempt.toEither

/*
Eithers are right-biased
functions are assumed to work on Right-part of either
 */
val plusTwo: Either[Throwable, Int] = resultEither.map(i => i + 2)

//Access to Left has to be explicit

val stringedThrowableEither: Either[String, Int] = resultEither.left.map(t => t.getMessage)

/*
Getting result; pattern match!
 */

val resultingString: String = stringedThrowableEither match {
  case Right(i) => i.toString
  case Left(message) => message
}


