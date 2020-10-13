object RecursionExample {
  /*
    Recursion
   */

  def unsafeSumList(l: List[Int]): Int = l match {
    case head :: rest => unsafeSumList(rest) + head
    case Nil          => 0
  }

  def sumListSafe(l: List[Int]): Int = {
    def loop(list: List[Int], sumSoFar: Int): Int =
      list match {
        case head :: rest => loop(rest, head + sumSoFar)
        case Nil          => 0
      }

    loop(l, 0)
  }
}
