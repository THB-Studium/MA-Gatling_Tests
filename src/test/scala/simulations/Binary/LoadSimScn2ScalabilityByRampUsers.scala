package simulations.Binary

import io.gatling.core.Predef._
import scenarios._
import utils.{DatabaseConf, TestHelper}
import utils.Headers._
import utils.TestHelper._
import utils.db.{DbName, TableName}

import scala.concurrent.duration._
import scala.language._

class LoadSimScn2ScalabilityByRampUsers extends Simulation {

  before {
    // to print the test config before the test execution
    println(s"::: Test Execution For Scalability In Terms Of The Number Of Users:")
    println(s"::: - $LS2_RAMP_USER_COUNT Virtual Users")
    println(s"::: - Ramping Users Over $LS2_RAMP_DURATION Seconds")
    println(s"::: - Max Test Duration: $LS2_TEST_DURATION Seconds")
    println(s"::: - Current Database: $CURRENT_DB")
  }

  after {
    val tableName: TableName.Value = if (!TestHelper.currentDB.equals(DbName.CassandraDB))
      TableName.BundleMod_Cassandra else TableName.BundleMod

    // to clean the DB after tests:
    val db = new DatabaseConf()
    db.cleanup(tableName)
  }


  setUp(BinaryScn.createBinaryScn.inject(rampUsers(LS2_RAMP_USER_COUNT) during (LS2_RAMP_DURATION seconds)))
    .protocols(httpProtocol)
    .maxDuration(LS2_TEST_DURATION seconds)
    .assertions(
      forAll.responseTime.max.lt(6000),
      forAll.failedRequests.percent.lte(0)
    )

}