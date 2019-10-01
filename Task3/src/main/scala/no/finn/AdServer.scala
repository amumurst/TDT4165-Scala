package no.finn

import no.finn.common._

class AdServer(db: AdDatabase[Ad], console: Console) {
  private def addAd(): Unit = {
    val adData: String       = console.readLine("Enter ad data: ")
    val parsedAd: Option[Ad] = Ad.fromString(adData)

    val insertedId: AdId = AdId(0) //TODO: Implement real insertion. Was previously "db.insert(ad)"
    console.printConsole(s"Inserted ad with id: $insertedId")
  }

  private def readAd(): Unit = {
    val adId: AdId = AdId(console.readLine("Enter adId: ").toLong)
    db.get(adId).foreach(ad => console.printConsole(ad.toConsoleString))
  }

  def start(): Unit = {
    var mode: Mode = UnknownMode

    while (mode != QuitMode) {
      val userInput = console.readLine("Select mode: quit, add, read: ")
      mode = Mode.fromString(userInput)

      mode match {
        case AddMode     => addAd()
        case ReadMode    => readAd()
        case UnknownMode => console.printConsole("unknown mode")
        case QuitMode    => console.printConsole("Goodbye")
      }
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = new AdServer(new AdDatabase[Ad], RealConsole).start()
}
