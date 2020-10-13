object OptionExample {
  /*
    Option is an sum type ADT with 2 instances
    Some(value) signals that there exists a value
    None signals the absence of a value

    In scala we use Options instead of null to signal absence.
   * */

  sealed trait MyOption[+A]
  case class MySome[A](a: A) extends MyOption[A]
  case object MyNone         extends MyOption[Nothing]

  //Java-esque absence
  def divideBy(x: Int, y: Int): Int = if (y == 0) null.asInstanceOf[Int] else x / y
  //scala-esque absence
  def divideByOpt(x: Int, y: Int): MyOption[Int] = if (y == 0) MyNone else MySome(x / y)

  val x = divideBy(1, 2)
  val y = x * 2 //Do I need to check for nullability here??

  val x2 = divideByOpt(1, 2)
  //The compiler forces us to handle both cases!
  val y2 = x2 match {
    case MyNone    => 0
    case MySome(a) => a * 2
  }

  /*
  Going from Option[A] to Option[B] via function A=>B
  use Option.map(function)
   */

  val longOption: Option[Long]  = "23".toLongOption //Some(23)
  val twiceOption: Option[Long] = longOption.map(l => l * 2) //Some(46)
}
