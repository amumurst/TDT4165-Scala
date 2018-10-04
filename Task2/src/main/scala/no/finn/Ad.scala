package no.finn

sealed trait Ad {
  def toConsoleString: String
}

object UnknownAdType extends Ad {
  override def toConsoleString: String = "UnknownAd"
}

object Ad {
  private def splitString(s: String): List[String] = s.split(' ').toList

  def fromString(s: String): Ad = {
    val stringList: List[String] = splitString(s)

    if (stringList(0) == "car") {
      ???
    } else if (stringList(0) == "job") {
      ???
    } else {
      UnknownAdType
    }
  }
}
