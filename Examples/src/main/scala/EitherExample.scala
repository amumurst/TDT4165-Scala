object EitherExample {
  /*
  Either is also a sum ADT which is more general than Option since it also can carry information about the error case

  sealed trait Either[+L, +R]
  case class Left[L](l: L) extends Either[L, Nothing]
  case class Right[R](r: R) extends Either[Nothing, R]

  Common practise is to treat the Right type as Success and the Left type as Error.

  A common use case is signaling errors from functions instead of throws
   */

  //Java-esque error passing
  def divider(x: Int, y: Int): Int =
    if (y == 0) throw new IllegalArgumentException("nooo not a 0 please") else x / y

  val x = divider(1, 2) //Do I need to wrap this in try-catch??
  val y = x * 2

  //Java-esque error passing
  def dividerE(x: Int, y: Int): Either[String, Int] =
    if (y == 0) Left("nooo not a 0 please") else Right(x / y)

  val x2 = dividerE(1, 2)
  val y2 = x2 match {
    case Left(value) =>
      0 //Here I have the choice of what to do with my error message. Pass it on or handle here?
    case Right(value) =>
      value * 2
  }

  //Lifting options into eithers
  val helloOption: Option[Int]             = Some(42)
  val resultEither: Either[Throwable, Int] = helloOption.toRight(new Throwable("No hello found"))

  /*
    Eithers are right-biased
    standard functions are assumed to work on Right-side
   */
  val plusTwo: Either[Throwable, Int] = resultEither.map(i => i + 2)

  //Access to Left has to be explicit
  val stringedThrowableEither: Either[String, Int] = resultEither.left.map(t => t.getMessage)
}
