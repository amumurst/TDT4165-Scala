package no.finn

sealed trait AdError {
  def message: String
}
//TODO: Add instances for errors
