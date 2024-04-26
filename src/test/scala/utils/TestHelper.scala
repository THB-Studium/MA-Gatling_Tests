package utils

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check._
import utils.db.DbName

object TestHelper {
  // ::: Constants Attributes
  val currentDB: DbName.Value       = DbName.PostgresQL
  val DELETE_SQL_QUERY: String      = "DELETE FROM %s"
  val DELETE_CQL_QUERY: String      = "TRUNCATE %s"

  val responseBodyLengthKey: String = "responseBodyLength"
  val responseBodyListKey: String   = "responseBodyList"
  val responseBodyKey: String       = "responseBody"
  val createdObjectKey: String      = "createdObject"
  val updatedObjectKey: String      = "updatedObject"
  val objectIdKey: String           = "objectId"




  // ::: Test Config
  def LS2_USER_COUNT: Int       = System.getProperty("LS2_USER_COUNT", "5000").toInt
  def LS2_RAMP_USER_COUNT: Int  = System.getProperty("LS2_RAMP_USER_COUNT", "10000").toInt
  def LS2_ONCE_USER_COUNT: Int  = System.getProperty("LS2_RAMP_USER_COUNT", "3").toInt
  def LS2_CONST_USER_COUNT: Int = System.getProperty("LS2_CONST_USER_COUNT", "1").toInt
  def LS2_RAMP_DURATION: Int    = System.getProperty("LS2_RAMP_DURATION", "60").toInt // in second
  def LS2_TEST_DURATION: Int    = System.getProperty("LS2_TEST_DURATION", "600").toInt // in second

  def CURRENT_DB: String        = System.getProperty("CURRENT_DB", currentDB.toString.toUpperCase())


  // ::: Functions
  def saveBodyLength: HttpCheck = {
    bodyBytes.transform(bytes => bytes.length).saveAs(responseBodyLengthKey)
  }

}