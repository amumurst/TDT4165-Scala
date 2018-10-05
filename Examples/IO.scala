import cats.effect.IO

/*Does function have side effects? (and how are they performed?)*/

def unknownFunction(i: Int): Unit = println(s"$i")

unknownFunction(123)
unknownFunction(23)


/*Function tells me it has side effects,
  and needs to be explicitly executed
*/
def unknownIOFunction(i: Int): IO[Unit] = IO(println(i))

unknownFunction(123)
unknownIOFunction(23)


/*Mapping results*/

val longIO: IO[Long] =IO.pure(23123)
val doubledIO: IO[Long] = longIO.map(l => l*2)


/*
Chaining
 */

val combinedIO: IO[Unit] = for {
  res1 <- unknownFunction(123)
  res2 <- unknownIOFunction(23)
  output <- if (1 > 20) IO.unit else unknownFunction(23)
} yield output

/*Running*/
combinedIO.unsafeRunSync()