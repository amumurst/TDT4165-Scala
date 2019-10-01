package no.finn.common

trait Console {
  def readLine(text: String): String
  def printConsole(text: String): Unit
}

object RealConsole extends Console {
  override def readLine(text: String): String = scala.io.StdIn.readLine(text)

  override def printConsole(text: String): Unit = println(text)
}
