package simulations.Bundle

import io.gatling.core.Predef._
import scenarios._
import utils.Headers._
import utils.TestHelper._
import utils.db.{DbName, TableName}
import utils.{DatabaseConf, TestHelper}

import scala.concurrent.duration._
import scala.language._

class LoadSimScn2ScalabilityAmountOfData extends Simulation {

  before {
    // to print the test config before the test execution
    println(s"::: Test Execution For Scalability In Terms Of The Amount Of Data:")
    println(s"::: - $LS2_RAMP_USER_COUNT Virtual Ramp Users")
    println(s"::: - $LS2_CONST_USER_COUNT Virtual Const Users")
    println(s"::: - Ramping Users Over $LS2_RAMP_DURATION Seconds")
    println(s"::: - Max Test Duration: $LS2_TEST_DURATION Seconds")
    println(s"::: - Current Database: $CURRENT_DB")
  }

  after {
    val tableName: String = if (!TestHelper.currentDB.equals(DbName.CassandraDB))
      TableName.BundleMod_Cassandra.toString else TableName.BundleMod.toString

    val db = new DatabaseConf()
    db.cleanup(tableName)
  }


  setUp(
    BundleScn.createBundleScn
      .inject(rampUsers(LS2_RAMP_USER_COUNT) during (LS2_RAMP_DURATION seconds))
      .andThen(BundleScn.listAllBundlesScn.inject(atOnceUsers(LS2_ONCE_USER_COUNT)))
  )
    .protocols(httpProtocol)
    .maxDuration(LS2_TEST_DURATION seconds)
    .assertions(
      forAll.responseTime.max.lt(6000), // in seconds
      forAll.failedRequests.percent.lte(0)
    )

}