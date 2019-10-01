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

object Ad {
  private def splitString(s: String): List[String] = s.split(' ').toList

  def fromString(s: String): Option[Ad] =
    splitString(s) match {
      case prod :: regNr :: price :: Nil if prod == "car" =>
        None // TODO: Implement
      case prod :: company :: salary :: Nil if prod == "job" =>
        None // TODO: Implement
      case _ => None
    }
}
