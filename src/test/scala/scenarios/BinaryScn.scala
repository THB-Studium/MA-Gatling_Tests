package scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import utils.TestHelper._
import utils._

object BinaryScn {
  private val binaryId: String = "bd1ff640-1061-4972-bde7-4a7e377166a7"

  // ::: Scenarios Definitions

  /** * Create A Binary ** */
  private def _createBinary(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Create A Binary")
        .post(Environment.binariesUrl)
        .body(ElFileBody("data/binary.json")).asJson
        .check(bodyString.saveAs(createdObjectKey))
        .check(status.is(200))
//        .check(jsonPath("$.id").notNull)
//        .check(jsonPath("$.id").saveAs(objectIdKey))
//        .check(jsonPath("$.resourceType").is(Environment.resourceType_binary))
//        .check(jsonPath("$.meta.versionId").is(Environment.meta_versionId))
//        .check(jsonPath("$.meta.lastUpdated").notNull)
//        .check(jsonPath("$.meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
//        .check(jsonPath("$.meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
//        .check(jsonPath("$.meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * List All Binaries ** */
  private def _listAllBinaries(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Fetch All Binaries")
        .get(Environment.binariesUrl)
        .check(status.is(200))
//        .check(bodyString.saveAs(responseBodyListKey))
//        .check(saveBodyLength)
//        .check(jsonPath("$[0].id").notNull)
//        .check(jsonPath("$[0].id").saveAs(objectIdKey))
//        .check(jsonPath("$[0].resourceType").is(Environment.resourceType_binary))
//        .check(jsonPath("$[0].meta.versionId").is(Environment.meta_versionId))
//        .check(jsonPath("$[0].meta.lastUpdated").notNull)
//        .check(jsonPath("$[0].meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
//        .check(jsonPath("$[0].meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
//        .check(jsonPath("$[0].meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * Get One Binary ** */
  private def _getOneBinary(): ChainBuilder = {
    exitBlockOnFail {
//      exec(http("Get One Binary by id=${" + objectIdKey + "}")
        //        .get(Environment.binariesUrl + "/${" + objectIdKey + "}")
      exec(http("Get One Binary by id=" + binaryId)
        .get(Environment.binariesUrl + "/" + binaryId)
        .check(status.is(200))
        .check(bodyString.saveAs(responseBodyKey))
        .check(jsonPath("$.resourceType").is(Environment.resourceType_binary))
        .check(jsonPath("$.meta.versionId").is(Environment.meta_versionId))
        .check(jsonPath("$.meta.lastUpdated").notNull)
        .check(jsonPath("$.meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
        .check(jsonPath("$.meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
        .check(jsonPath("$.meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * Update Binary ** */
  private def _updateOneBinary(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Update Binary by id=${" + objectIdKey + "}")
        .get(Environment.binariesUrl + "/${" + objectIdKey + "}")
        .body(ElFileBody("data/binary_update.json")).asJson
        .check(status.is(200))
        .check(bodyString.saveAs(updatedObjectKey))
        .check(jsonPath("$.resourceType").is(Environment.resourceType_binary))
        .check(jsonPath("$.meta.versionId").is(Environment.meta_versionId))
        .check(jsonPath("$.meta.lastUpdated").notNull)
        .check(jsonPath("$.meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
        .check(jsonPath("$.meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
        .check(jsonPath("$.meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * delete Binary ** */
  private def _deleteOneBinary(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Delete Binary by id=${" + objectIdKey + "}")
        .delete(Environment.binariesUrl + "/${" + objectIdKey + "}")
        .check(status.is(200))
      )
    }
  }


  // ::: Scenarios expose

  val createBinaryScn: ScenarioBuilder = scenario("Scenario To Create Binary")
    .exec(_createBinary())

  val listAllBinariesScn: ScenarioBuilder = scenario("Scenario To Fetch Binaries")
    .exec(_listAllBinaries())

  val getOneBinaryScn: ScenarioBuilder = scenario("Scenario To Get One Binary")
    .exec(_getOneBinary())

  val updateOneBinaryScn: ScenarioBuilder = scenario("Scenario To Update One Binary")
    .exec(_updateOneBinary())

  val deleteOneBinaryScn: ScenarioBuilder = scenario("Scenario To Delete One Binary")
    .exec(_deleteOneBinary())

  val createAndListAllBinariesScn: ScenarioBuilder = scenario("Scenario To Create And FetchOne Binaries")
    .exec(_createBinary())
    .exec(_listAllBinaries())

  val binaryScenarios: ScenarioBuilder = scenario("All Binary Scenarios")
    .exec(_createBinary())
    .exec(_listAllBinaries())
    .exec(_getOneBinary())
    .exec(_updateOneBinary())
    .exec(_deleteOneBinary())

}