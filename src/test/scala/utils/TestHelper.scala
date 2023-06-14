package utils

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check._

object TestHelper {
  // ::: Constants Attributes
  val responseBodyLengthKey: String = "responseBodyLength"
  val responseBodyListKey: String   = "responseBodyList"
  val responseBodyKey: String       = "responseBody"
  val createdObjectKey: String      = "createdObject"
  val updatedObjectKey: String      = "updatedObject"
  val objectIdKey: String           = "objectId"


  def LS2_USER_COUNT: Int    = System.getProperty("LS2_USER_COUNT", "10000").toInt // 1000
  def LS2_RAMP_DURATION: Int = System.getProperty("LS2_RAMP_DURATION", "60").toInt
  def LS2_TEST_DURATION: Int = System.getProperty("LS2_TEST_DURATION", "600").toInt // in second: 600s = 10min


  // ::: Functions
  def saveBodyLength: HttpCheck = {
    bodyBytes.transform(bytes => bytes.length).saveAs(responseBodyLengthKey)
  }

}