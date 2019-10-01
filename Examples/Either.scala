object EitherExample {
    /*
  Either is also an ADT

  trait Either[L, R]

  case class Left[L](l: L) extends Either[L, Nothing]
  case class Right[R](r: R) extends Either[Nothing, R]
   */


  val helloOption: Option[Int] = Some(42)
  val resultEither: Either[Throwable, Int] = helloOption.toRight(new Throwable("No hello found"))


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
}