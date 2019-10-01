package no.solution

import cats.effect.IO
import no.finn.common._

class AdServer(db: AdDatabase[Ad], console: Console) {
  def readConsoleIO(text: String)  = IO(console.readLine(text))
  def printConsoleIO(text: String) = IO(console.printConsole(text))

  private val addAd: IO[Unit] =
    for {
      adData <- readConsoleIO("Enter ad data: ")
      a <- Ad.fromString(adData) match {
            case Left(value) => printConsoleIO(value.message)
            case Right(ad) =>
              val insertedId: AdId = db.insert(ad)
              printConsoleIO(s"Inserted ad with id: $insertedId")
          }
    } yield ()

  private val readAd: IO[Unit] =
    for {
      adId <- readConsoleIO("Enter adId: ").map(s => AdId(s.toLong))
      _    <- printConsoleIO(db.get(adId).get.toConsoleString)
    } yield ()

  val start: IO[Unit] =
    for {
      userInput <- readConsoleIO("Select mode: quit, add, read: ")
      mode      = Mode.fromString(userInput)
      _ <- mode match {
            case AddMode     => addAd
            case ReadMode    => readAd
            case UnknownMode => printConsoleIO("unknown mode")
            case QuitMode    => printConsoleIO("Goodbye")
          }
      runAgain <- if (mode != QuitMode) start else IO.unit
    } yield runAgain
}

object Main {
  def main(args: Array[String]): Unit =
    new AdServer(new AdDatabase[Ad], RealConsole).start.unsafeRunSync()
}
