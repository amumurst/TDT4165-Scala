package no.finn.common

trait TestConsole extends Console {
  def inputs: List[String]
  var outputs: List[String] = List.empty

  var currentInput  = 0
  var currentOutPut = 0

  override def readLine(text: String): String = {
    val temp = inputs(currentInput)
    currentInput = currentInput + 1
    temp
  }

  override def printConsole(text: String): Unit =
    outputs = outputs :+ text
}
