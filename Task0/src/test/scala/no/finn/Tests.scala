package no.finn

import no.finn.common._
import org.scalatest.FunSuite

trait TestConsole extends Console {
  val inputs: List[String]
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

class Tests extends FunSuite {

  def getServer(inputStrings: List[String]): AdServer with TestConsole =
    new AdServer with TestConsole {
      override val inputs: List[String] = inputStrings
    }

  test("Implementation") {
    val inputs =
      List("add", "car mercedes 4000", "add", "job MyFirm 100", "read", "0", "read", "1", "quit")
    val expectedOutputs =
      List("Inserted ad with id: AdId(0)",
           "Inserted ad with id: AdId(1)",
           "car mercedes 4000",
           "job MyFirm 100",
           "Goodbye")

    val server = getServer(inputs)

    server.run()
    val outputs = server.outputs

    assert(outputs === expectedOutputs)
  }

}
