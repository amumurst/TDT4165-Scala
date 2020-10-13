package no.solution

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

  def fromString(s: String): Either[AdError, Ad] =
    splitString(s) match {
      case "car" :: regNr :: price :: Nil =>
        price.toLongOption
          .toRight(LongParseError(s"price was badly formated [$price]"))
          .map(long => CarAd(regNr, long))
      case "job" :: company :: salary :: Nil =>
        salary.toLongOption
          .toRight(LongParseError(s"salary was badly formated [$salary]"))
          .map(long => JobAd(company, long))
      case _ => Left(ArgumentsError("Bad arguments!"))
    }
}
