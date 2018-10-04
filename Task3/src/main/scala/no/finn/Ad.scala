package no.finn

sealed trait Ad {
  def toConsoleString: String
}

case class CarAd(regNr: String, price: Long) extends Ad {
  override def toConsoleString: String = s"car $regNr $price"
}
case class JobAd(company: String, monthlySalary: Long) extends Ad {
  override def toConsoleString: String = s"job $company $monthlySalary"
}
object UnknownAdType extends Ad {
  override def toConsoleString: String = "UnknownAd"
}

object Ad {
  private def splitString(s: String): List[String] = s.split(' ').toList

  private implicit class SafeStringToLong(private val s: String) {
    def toLongOpt: Option[Long] = scala.util.Try(s.toLong).toOption
  }

  def fromString(s: String): Option[Ad] =
    splitString(s) match {
      case prod :: regNr :: price :: Nil if prod == "car" =>
        ???
      case prod :: company :: salary :: Nil if prod == "job" =>
        ???
      case _ => None
    }
}
