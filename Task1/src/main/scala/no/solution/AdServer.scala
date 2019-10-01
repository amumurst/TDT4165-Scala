package no.solution

import no.finn.common._

class AdServer(db: AdDatabase[String], console: Console) {
  private def addAd(): Unit = {
    val adData: String   = console.readLine("Enter ad data: ")
    val insertedId: AdId = db.insert(adData)
    console.printConsole(s"Inserted ad with id: $insertedId")
  }

  private def readAd(): Unit = {
    val adId: AdId = AdId(console.readLine("Enter adId: ").toLong)
    console.printConsole(db.get(adId).get)
  }

  def start(): Unit = {
    var mode: Mode = UnknownMode

    while (mode != QuitMode) {
      val userInput = console.readLine("Select mode: quit, add, read: ")
      mode = Mode.fromString(userInput)

      mode match {
        case QuitMode    => console.printConsole("Goodbye")
        case ReadMode    => readAd()
        case AddMode     => addAd()
        case UnknownMode => console.printConsole("unknown mode")
      }
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = new AdServer(new AdDatabase[String], RealConsole).start()
}
