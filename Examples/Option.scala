object OptionExample {
  /*
  * Pattern matching on lists
  * */

  val myList = List(1, 2, 42)

  val trippleSum = myList match {
    case firstElement :: secElement :: thirdElement :: Nil => firstElement + secElement + thirdElement
    case elements if elements.isEmpty => -1
    case _ => 0
  }


  /*
  Option is an ADT!
  * */

  trait MyOption[A]

  case class MySome[A](a: A) extends MyOption[A]

  case object MyNone extends MyOption[Nothing]


  /*
  Going from Option[A] to Option[B] via function A=>B
  use Option.map(function)
   */

  val longOption: Option[Long] = "23".toLongOption
  val twiceOption: Option[Long] = longOption.map(l => l * 2)

  twiceOption.foreach(t => println(t))
}