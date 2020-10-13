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

  //TODO: Change return type to Either[AdError, Ad]
  def fromString(s: String): Option[Ad] =
    splitString(s) match {
      case "car" :: regNr :: price :: Nil =>
        price.toLongOption.map(long => CarAd(regNr, long))
      case "job" :: company :: salary :: Nil =>
        salary.toLongOption.map(long => JobAd(company, long))
      case _ => None
    }
}
