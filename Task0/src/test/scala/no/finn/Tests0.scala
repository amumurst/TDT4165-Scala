package no.finn

import no.finn.common._
import org.scalatest.funsuite.AnyFunSuite

class Tests0 extends AnyFunSuite {
  test("Normal usage") {
    val testConsole = new TestConsole {
      override val inputs: List[String] =
        List("add", "car mercedes 4000", "add", "job MyFirm 100", "read", "0", "read", "1", "quit")
    }

    val expectedOutputs =
      List("Inserted ad with id: AdId(0)",
           "Inserted ad with id: AdId(1)",
           "car mercedes 4000",
           "job MyFirm 100",
           "Goodbye")

    val server = new AdServer(new AdDatabase[String], testConsole)

    server.start()
    val outputs = testConsole.outputs

    assert(outputs === expectedOutputs)
  }

}
