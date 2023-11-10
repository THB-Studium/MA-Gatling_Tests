package simulations.Bundle

import io.gatling.core.Predef._
import scenarios._
import utils.DatabaseConf
import utils.Headers._
import utils.TestHelper._
import utils.db.TableName

import scala.concurrent.duration._
import scala.language._

class LoadSimScn2CreateBundle extends Simulation {

  before {
    println(s"::: Test Execution For Create Bundle With $LS2_USER_COUNT Virtual Users")
    println(s"::: Ramping Users Over $LS2_RAMP_DURATION Seconds")
    println(s"::: Total Test Duration: $LS2_TEST_DURATION Seconds")
  }

  after {
    // to clean the DB after tests:
    val db = new DatabaseConf()
    db.cleanup(TableName.BundleMod.toString)
  }


  setUp(
    BundleScn.createBundleScn.inject(rampUsers(LS2_USER_COUNT) during (LS2_RAMP_DURATION seconds))
  )
    .protocols(httpProtocol)
    .maxDuration(LS2_TEST_DURATION seconds)
    .assertions(
      forAll.responseTime.max.lt(6000),
      forAll.failedRequests.percent.lte(0))

}