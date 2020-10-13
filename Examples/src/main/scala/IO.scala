object IOExample {
  import cats.effect.IO

  /*Does function have side effects? (and how are they performed?)*/

  def unknownFunction(i: Int): Unit = println(s"$i")

  unknownFunction(123)
  unknownFunction(23)

  /*Function tells me it has side effects,
    and needs to be explicitly executed
   */
  def unknownIOFunction(i: Int): IO[Int] = IO(println(i)).map(_ => i)

  unknownFunction(123)
  unknownIOFunction(23)

  /*Mapping results*/

  val longIO: IO[Long]    = IO.pure(23123)
  val doubledIO: IO[Long] = longIO.map(l => l * 2)

  /*
    Chaining
   */

  val combinedIO: IO[Unit] = for {
    res1      <- unknownIOFunction(12)
    res2      <- unknownIOFunction(23)
    something = res1 + res2
    _         <- if (1 > 20) IO.unit else unknownIOFunction(23)
  } yield ()

  /*Running*/
  combinedIO.unsafeRunSync()
}
