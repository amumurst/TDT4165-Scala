object IOExample {
  import cats.effect.IO

  /***
    Referential transparency

    An expression is called referentially transparent if it can be replaced
    with its corresponding value without changing the program's behavior.
    */
  //Consider this function
  def multiply(x: Int, y: Int): Int = {
    val result = x * y
    println(s"$x*$y=$result")
    result
  }

  //So in a function like this we print twice
  val complicatedFunction: Int = {
    val power1 = multiply(3, 5)
    val power2 = multiply(3, 5)

    power1 + power2
  }

  //By replacing with return value we dont print anything!
  val simplifiedFunction: Int = {
    val power1 = 15
    val power2 = 15

    power1 + power2
  }

  //But after refactoring like this we print only once, even though we have just replaced values with each other
  val power12: Int = multiply(3, 5)
  val refactoredComplicatedFunction: Int = {
    val power1 = power12
    val power2 = power12

    power1 + power2
  }

  /***
   IO is a datatype that describes a program eg. something that can be executed
    Nothing is executed at creating.
    We then collect and combined describtions of programs and execute them only
    in the main function of our app
    */
  def multiplyIO(x: Int, y: Int): IO[Int] = IO {
    val result = x * y
    println(s"$x*$y=$result")
    result
  }

  //So in a function like this we print twice
  val complicatedFunctionIO: IO[Int] =
    for {
      p1 <- multiplyIO(3, 5)
      p2 <- multiplyIO(3, 5)
    } yield p1 + p2
  complicatedFunctionIO.unsafeRunSync() //Runs app and prints twice

  val power12IO: IO[Int] = multiplyIO(3, 5)
  val refactoredComplicatedFunctionIO: IO[Int] =
    for {
      p1 <- power12IO
      p2 <- power12IO
    } yield p1 + p2
  refactoredComplicatedFunctionIO.unsafeRunSync() //Runs app and prints twice

  //The refactored version is now equivalent to the pre-refactored version, and guaranteed by the compiler!

  //Common things that make functions non referential transparent:
  // Database calls, HTTP calls, Logging, Println, Monitoring, Functions using random
  //These things are commonly called "Side Effects" since they interact outside the program it self
}
