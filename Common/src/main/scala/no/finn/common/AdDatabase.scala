package no.finn.common

import scala.collection.mutable.{Map => MutMap}

/*
  Database simulator with AdId as primary key and AdDataType as columns
 */
class AdDatabase[AdDataType] {
  //Simulates key-value database
  private val memory: MutMap[AdId, AdDataType] = MutMap.empty

  //Simulates auto incrementing primary key
  private var primaryKey: AdId = AdId(0)

  private def getAdId: AdId = {
    val temp = primaryKey
    primaryKey = AdId(primaryKey.id + 1)
    temp
  }

  def insert(data: AdDataType): AdId = {
    val nextPrimaryKey = getAdId
    memory(nextPrimaryKey) = data
    nextPrimaryKey
  }
  def get(id: AdId): Option[AdDataType] = memory.get(id)
}
