package no.solution

import no.finn.common._

trait AdServer extends Server with Database[Ad] {

  private def addAd(): Unit = {
    val adData: String       = readLine("Enter ad data: ")
    val parsedAd: Option[Ad] = Ad.fromString(adData)

    parsedAd.foreach { ad =>
      val insertedId: AdId = insertInDatabase(ad)
      printConsole(s"Inserted ad with id: $insertedId")
    }
  }

  private def readAd(): Unit = {
    val adId: AdId = AdId(readLine("Enter adId: ").toLong)
    printConsole(getFromDatabase(adId).get.toConsoleString)
  }

  def run(): Unit = {
    var mode: Mode = UnknownMode

    while (mode != QuitMode) {
      mode = Mode.fromString(readLine("Select mode: quit, add, read: "))

      mode match {
        case AddMode     => addAd()
        case ReadMode    => readAd()
        case UnknownMode => printConsole("unknown mode")
        case QuitMode    => printConsole("Goodbye")
      }
    }
  }
}

object MainSolution extends AdServer with RealConsole {
  def main(args: Array[String]): Unit =
    run()
}
