object ADTExample {
  /*
   * Sum type (ADT) for Pet
   * */
  sealed trait Pet
  case class Cat(name: String)               extends Pet
  case class Fish(name: String, teeth: Long) extends Pet
  case class Squid(name: String, age: Int)   extends Pet
  case object LochNessMonster                extends Pet

  val someCutCat = Cat("Pusefinn")
  val funySquid  = Squid("Squidward", 3)

  val petList: List[Pet] = List(someCutCat, funySquid)

  /*Introducing a function that takes any pet */
  def royalPetName(p: Pet): String = p match {
    case Cat(name)                     => s"Emperor $name"
    case Fish(name, _)                 => s"Baron $name"
    case Squid(name, age) if age >= 10 => s"Duke $name"
    case Squid(name, age) if age < 10  => s"Prince $name"
    case LochNessMonster               => s"Nessie the Loch Ness Monster"
  }

  val royalPetNames: List[String] = petList.map(royalPetName)
}
