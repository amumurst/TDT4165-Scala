package no.finn.common

/*
  Case class gives us:
    1. A default constructor
    2. All parameters are public
    3. A correct hash function
    4. An equals method that checks correctly

  By extending AnyVal we remove boxing
 */

case class AdId(id: Long) extends AnyVal
