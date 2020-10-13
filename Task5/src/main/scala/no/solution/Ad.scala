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
        price.toLongOption match {
          case Some(value) => Right(CarAd(regNr, value))
          case None        => Left(LongParseError(s"price was badly formated [$price]"))
        }
      case "job" :: company :: salary :: Nil =>
        salary.toLongOption match {
          case Some(value) => Right(JobAd(company, value))
          case None        => Left(LongParseError(s"price was badly formated [$salary]"))
        }
      case _ => Left(ArgumentsError(s"Bad arguments! $s"))
    }
}
