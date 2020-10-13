object ModelingExample {

  /*
   *
   * Pets have names and makes sounds
   * */
  sealed trait Pet {
    val name: String
    val makesSound: String
  }

  case class Cat(name: String) extends Pet {
    override val makesSound: String = "meow"
  }

  case class Dog(name: String) extends Pet {
    override val makesSound: String = "bark"
  }

  /*
   * Interpolation of string (adding s before " and using $ to escape
   * Use ${a.b} if you need to access field b in value a
   * */

  def getInterpolatedString(v1: String, v2: Int) =
    s"This string is interpolated, with values $v1 and $v2"

  /*
   * Strings can be cast to long (warning, this is an unsafe operation)
   * */
  val numberAsString: String = "23123"
  val numberAsLong: Long     = numberAsString.toLong
}
