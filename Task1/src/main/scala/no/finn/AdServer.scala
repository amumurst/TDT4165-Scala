package no.finn

import no.finn.common._

trait AdServer extends Server with Database[String] {
  private def addAd(): Unit = {
    val adData: String   = readLine("Enter ad data: ")
    val insertedId: AdId = insertInDatabase(adData)
    printConsole(s"Inserted ad with id: $insertedId")
  }

  private def readAd(): Unit = {
    val adId: AdId = AdId(readLine("Enter adId: ").toLong)
    printConsole(getFromDatabase(adId).get)
  }

  def run(): Unit = {
    var mode: String = ""

    while (mode != "quit") {
      mode = readLine("Select mode: quit, add, read: ")

      if (mode == "add") {
        addAd()
      } else if (mode == "read") {
        readAd()
      } else if (mode == "quit") {
        printConsole("Goodbye")
      } else {
        printConsole("unknown mode")
      }
    }
  }
}

object Main extends AdServer with RealConsole {
  def main(args: Array[String]): Unit =
    run()
}
