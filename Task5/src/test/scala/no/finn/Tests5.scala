package no.finn

import no.finn.common._
import org.scalatest.funsuite.AnyFunSuite

class Tests5 extends AnyFunSuite {
  test("Normal usage") {
    val testConsole = new TestConsole {
      override val inputs: List[String] =
        List("add",
             "car mercedes 4000",
             "add",
             "job MyFirm 100",
             "read",
             "0",
             "read",
             "1",
             "add",
             "boat 500",
             "add",
             "car mercedes 400kr",
             "quit")
    }

    val expectedOutputs =
      List(
        "Inserted ad with id: AdId(0)",
        "Inserted ad with id: AdId(1)",
        "car mercedes 4000",
        "job MyFirm 100",
        "Bad arguments! boat 500",
        "price was badly formated [400kr]",
        "Goodbye"
      )

    val server = new AdServer(new AdDatabase[Ad], testConsole)

    server.start()
    val outputs = testConsole.outputs

    outputs.zip(expectedOutputs).foreach { case (a, b) => assert(a === b) }
  }

  def adCyclicalTest(ad: Option[Ad]) =
    assert(ad.flatMap(a => Ad.fromString(a.toConsoleString).toOption) === ad)

  test("Ad parsing is cyclical for car ad") {
    adCyclicalTest(Some(CarAd("aj12332", 5000)))
  }
  test("Ad parsing is cyclical for job ad") {
    adCyclicalTest(Some(JobAd("FINN.no", 9999999)))
  }
  test("Ad parsing is cyclical for nothing") {
    adCyclicalTest(None)
  }
}
