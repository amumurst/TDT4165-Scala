/*
Recursion
 */

def unsafeSumList(l: List[Int]): Int = l match {
  case head::rest => unsafeSumList(rest) + head
}

def sumListSafe(l: List[Int]): Int = {
  def loop(list: List[Int], sumSoFar: Int): Int =
    list match {
      case head::rest => loop(rest, head + sumSoFar)
    }
  loop(l, 0)
}

