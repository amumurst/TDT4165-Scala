package no.finn

import no.finn.common._

import cats.effect.IO

trait AdServer extends Server with Database[Ad] {

  def printConsoleIO(s: String): IO[Unit]       = IO(printConsole(s))
  def readStringIO(message: String): IO[String] = IO(readLine(message))

  private def addAd(): IO[Unit] =
    for {
      adData <- readStringIO("Enter ad data: ")
      output <- Ad.fromString(adData) match {
                  case Left(err) => printConsoleIO(err.message)
                  case Right(ad) =>
                    val insertedId: AdId = insertInDatabase(ad)
                    printConsoleIO(s"Inserted ad with id: $insertedId")
                }
    } yield output

  private def readAd(): Unit = { //TODO: Wrap in IO
    val adId: AdId = AdId(readLine("Enter adId: ").toLong)
    printConsole(getFromDatabase(adId).get.toConsoleString)
  }

  def loop(): IO[Unit] = { //TODO: Implement as for-comprehension in IO monad
    val mode = Mode.fromString(readLine("Select mode: quit, add, read: "))

    mode match {
      case AddMode     => addAd()
      case ReadMode    => readAd()
      case UnknownMode => printConsole("unknown mode")
      case QuitMode    => printConsole("Goodbye")
    }
    if (mode != QuitMode) run() else ()
  }

  def run(): Unit = loop().unsafeRunSync()

}

object Main extends AdServer with RealConsole {
  def main(args: Array[String]): Unit =
    run()
}
