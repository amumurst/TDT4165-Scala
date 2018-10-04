package no.finn

sealed trait Mode
case object QuitMode    extends Mode
case object ReadMode    extends Mode
case object AddMode     extends Mode
case object UnknownMode extends Mode

object Mode {
  def fromString(s: String): Mode =
    s match {
      case "quit" => QuitMode
      case "read" => ReadMode
      case "add"  => AddMode
      case _      => UnknownMode
    }
}
