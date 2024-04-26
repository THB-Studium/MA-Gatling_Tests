package simulations.Binary

import io.gatling.core.Predef._
import scenarios._
import utils.DatabaseConf
import utils.Headers._
import utils.TestHelper._
import utils.db.TableName

import scala.concurrent.duration._
import scala.language._

class LoadSimScn2Binary extends Simulation {

  before {
    // to print the test config before the test execution
    println(s"::: Test Execution For Binary With $LS2_USER_COUNT Virtual Users")
    println(s"::: Ramping Users Over $LS2_RAMP_DURATION Seconds")
    println(s"::: Total Test Duration: $LS2_TEST_DURATION Seconds")
  }

  after {
    // to clean the DB after tests:
    val db = new DatabaseConf()
    db.cleanup(TableName.BinaryMod)
  }


  setUp(BinaryScn.binaryScenarios.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds)))
    .protocols(httpProtocol)
    .maxDuration(LS2_TEST_DURATION seconds)
    .assertions(
      forAll.responseTime.max.lt(6000), // in seconds
      forAll.failedRequests.percent.lte(0)
    )

}