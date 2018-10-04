package no.solution

import no.finn.common._

import cats.effect.IO

trait AdServer extends Server with Database[Ad] {

  def printConsoleIO(s: String): IO[Unit]       = IO(printConsole(s))
  def readStringIO(message: String): IO[String] = IO(readLine(message))

  private def addAd(): IO[Unit] =
    for {
      adData <- readStringIO("Enter ad data: ")
      output <- Ad
                 .fromString(adData)
                 .fold(err => printConsoleIO(err.message), ad => {
                   val insertedId: AdId = insertInDatabase(ad)
                   printConsoleIO(s"Inserted ad with id: $insertedId")
                 })
    } yield output

  private def readAd(): IO[Unit] =
    for {
      adId   <- readStringIO("Enter adId: ").map(s => AdId(s.toLong))
      output <- printConsoleIO(getFromDatabase(adId).get.toConsoleString)
    } yield output

  def loop(): IO[Unit] =
    for {
      mode <- readStringIO("Select mode: quit, add, read: ").map(Mode.fromString)
      _ <- mode match {
            case AddMode     => addAd()
            case ReadMode    => readAd()
            case UnknownMode => printConsoleIO("unknown mode")
            case QuitMode    => printConsoleIO("Goodbye")
          }
      runAgain <- if (mode != QuitMode) loop() else IO.unit
    } yield runAgain

  def run(): Unit = loop().unsafeRunSync()

}

object MainSolution extends AdServer with RealConsole {
  def main(args: Array[String]): Unit =
    run()
}
