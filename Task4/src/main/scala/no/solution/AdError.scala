package no.solution

sealed trait AdError {
  def message: String
}

case class ArgumentsError(message: String) extends AdError
case class LongParseError(message: String) extends AdError
