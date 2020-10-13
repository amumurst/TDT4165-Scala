package no.finn

import cats.effect.IO
import no.finn.common._

class AdServer(db: AdDatabase[Ad], console: Console) {
  def readConsoleIO(text: String): IO[String] = IO(console.readLine(text))
  def printConsoleIO(text: String): IO[Unit]  = IO(console.printConsole(text))

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

  //TODO: use readConsoleIO instead of console.readLine
  private def readAd(): Unit = {
    val adId: AdId = AdId(console.readLine("Enter adId: ").toLong)
    console.printConsole(db.get(adId).get.toConsoleString)
  }

  //TODO: Implement as for-comprehension in the IO monad eg. for{... <- ...} yield ()
  def start(): IO[Unit] = {

    val userInput = console.readLine("Select mode: quit, add, read: ") //TODO: Replace with IO variant
    val mode      = Mode.fromString(userInput)

    mode match {
      case AddMode     => addAd.unsafeRunSync() //TODO: Remove call to unsafeRunSync()
      case ReadMode    => readAd() //TODO: Replace with IO variant
      case UnknownMode => console.printConsole("unknown mode") //TODO: Replace with IO variant
      case QuitMode    => console.printConsole("Goodbye") //TODO: Replace with IO variant
    }
    if (mode != QuitMode) start() else IO.unit
  }
}

object Main {
  def main(args: Array[String]): Unit =
    new AdServer(new AdDatabase[Ad], RealConsole).start().unsafeRunSync()
}
