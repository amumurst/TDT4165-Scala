package no.finn.common

import scala.collection.mutable.{Map => MutMap}

case class AdId(id: Long) extends AnyVal

/*
  Database simulator with AdId as primary key and DataType as columns
 */
trait Database[DataType] {
  //Simulates key-vale database
  private val memory: MutMap[AdId, DataType] = MutMap.empty
  private var previousId: AdId               = AdId(0)

  private def getAdId: AdId = {
    val temp = previousId
    previousId = AdId(previousId.id + 1)
    temp
  }

  def insertInDatabase(data: DataType): AdId = {
    val insertedOnAdId = getAdId
    memory(insertedOnAdId) = data
    insertedOnAdId
  }
  def getFromDatabase(id: AdId): Option[DataType] = memory.get(id)

}
