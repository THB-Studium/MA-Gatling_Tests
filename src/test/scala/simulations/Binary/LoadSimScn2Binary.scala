package simulations.Binary

import io.gatling.core.Predef._
import scenarios._
import utils.Headers._
import utils.TestHelper._

import scala.concurrent.duration._
import scala.language._

class LoadSimScn2Binary extends Simulation {

  before {
    println(s"::: Test Execution For Binary With $LS2_USER_COUNT Virtual Users")
    println(s"::: Ramping Users Over $LS2_RAMP_DURATION Seconds")
    println(s"::: Total Test Duration: $LS2_TEST_DURATION Seconds")
  }


  setUp(

    /** * Test To Create Binary ** */
    BinaryScn.createBinaryScn.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds)),

    /** * Test To Fetch Binaries ** */
    BinaryScn.listAllBinariesScn.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds)),

    /** * Test To Get One Binary ** */
    BinaryScn.getOneBinaryScn.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds)),

    /** * Test To Update One Binary ** */
    BinaryScn.updateOneBinaryScn.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds)),

    /** * Test To Delete One Binary ** */
//    BinaryModScn.deleteOneBinaryScn.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds))

  )
    .protocols(httpProtocol)
    .maxDuration(LS2_TEST_DURATION seconds)
    .assertions(
      forAll.responseTime.max.lt(6000),
      forAll.failedRequests.percent.lte(0))

}