package no.finn

import no.finn.common._

trait AdServer extends Server with Database[Ad] {

  private def addAd(): Unit = {
    val adData: String       = readLine("Enter ad data: ")
    val parsedAd: Option[Ad] = Ad.fromString(adData)

    // Insert into database if exists
    // run printConsole(s"Inserted ad with id: $insertedId") on inserted ad

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

object Main extends AdServer with RealConsole {
  def main(args: Array[String]): Unit =
    run()
}
