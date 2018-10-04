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

  private implicit class SafeStringToLong(private val s: String) {
    def toLongEither: Either[LongParseError, Long] =
      scala.util.Try(s.toLong).toEither.left.map(t => LongParseError(t.getMessage))
  }

  def fromString(s: String): Either[AdError, Ad] =
    splitString(s) match {
      case prod :: regNr :: price :: Nil if prod == "car" =>
        price.toLongEither.map(long => CarAd(regNr, long))
      case prod :: company :: salary :: Nil if prod == "job" =>
        salary.toLongEither.map(long => JobAd(company, long))
      case _ => Left(ArgumentsError(s"$s is not a legal input"))
    }
}
