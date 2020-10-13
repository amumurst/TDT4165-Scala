package no.finn

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
    //TODO: change type to Mode
    var mode: String = ""

    while (mode != "quit") {
      mode = console.readLine("Select mode: quit, add, read: ")

      if (mode == "add") {
        addAd()
      } else if (mode == "read") {
        readAd()
      } else if (mode == "quit") {
        console.printConsole("Goodbye")
      } else {
        console.printConsole("unknown mode")
      }
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = new AdServer(new AdDatabase[String], RealConsole).start()
}
