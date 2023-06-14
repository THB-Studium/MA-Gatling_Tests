package scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import utils.TestHelper._
import utils._

object BundleScn {
  // ::: Scenarios Definitions

  /** * Create A Bundle ** */
  private def _createBundle(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Create A Bundle")
        .post(Environment.bundlesUrl)
        .body(ElFileBody("data/bundle.json")).asJson
        .check(status.is(200))
        .check(bodyString.saveAs(responseBodyKey))
//        .check(jsonPath("$.id").notNull)
//        .check(jsonPath("$.id").saveAs(objectIdKey))
//        .check(jsonPath("$.resourceType").is(Environment.resourceType_bundle))
//        .check(jsonPath("$.meta.versionId").is(Environment.meta_versionId))
//        .check(jsonPath("$.meta.lastUpdated").notNull)
//        .check(jsonPath("$.meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
//        .check(jsonPath("$.meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
//        .check(jsonPath("$.meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * List All Bundles ** */
  private def _listAllBundles(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Fetch All Bundles")
        .get(Environment.bundlesUrl)
        .check(status.is(200))
        .check(bodyString.saveAs(responseBodyListKey))
        .check(saveBodyLength)
//        .check(jsonPath("$[0].id").notNull)
//        .check(jsonPath("$[0].id").saveAs(objectIdKey))
//        .check(jsonPath("$[0].resourceType").is(Environment.resourceType_bundle))
//        .check(jsonPath("$[0].meta.versionId").is(Environment.meta_versionId))
//        .check(jsonPath("$[0].meta.lastUpdated").notNull)
//        .check(jsonPath("$[0].meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
//        .check(jsonPath("$[0].meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
//        .check(jsonPath("$[0].meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * Get One Bundle ** */
  private def _getOneBundle(): ChainBuilder = {
    exitBlockOnFail {
//      exec(http("Get One Bundle by id=${" + objectIdKey + "}")
      exec(http("Get One Bundle by id=${" + objectIdKey + "}")
        .get(Environment.bundlesUrl + "/${" + objectIdKey + "}")
        .check(status.is(200))
        .check(bodyString.saveAs(responseBodyKey))
//        .check(jsonPath("$.resourceType").is(Environment.resourceType_bundle))
//        .check(jsonPath("$.meta.versionId").is(Environment.meta_versionId))
//        .check(jsonPath("$.meta.lastUpdated").notNull)
//        .check(jsonPath("$.meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
//        .check(jsonPath("$.meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
//        .check(jsonPath("$.meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * Update Bundle ** */
  private def _updateOneBundle(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Update Bundle by id=${" + objectIdKey + "}")
        .get(Environment.bundlesUrl + "/${" + objectIdKey + "}")
        .body(ElFileBody("data/bundle_update.json")).asJson
        .check(status.is(200))
        .check(bodyString.saveAs(updatedObjectKey))
//        .check(jsonPath("$.resourceType").is(Environment.resourceType_bundle))
//        .check(jsonPath("$.meta.versionId").is(Environment.meta_versionId))
//        .check(jsonPath("$.meta.lastUpdated").notNull)
//        .check(jsonPath("$.meta.tag[0].system.myStringValue").is(Environment.meta_tag_system))
//        .check(jsonPath("$.meta.tag[0].code.myStringValue").is(Environment.meta_tag_code))
//        .check(jsonPath("$.meta.tag[0].display").is(Environment.meta_tag_display))
      )
    }
  }

  /** * delete Bundle ** */
  private def _deleteOneBundle(): ChainBuilder = {
    exitBlockOnFail {
      exec(http("Delete Bundle by id=${" + objectIdKey + "}")
        .delete(Environment.bundlesUrl + "/${" + objectIdKey + "}")
        .check(status.is(200))
      )
    }
  }


  // ::: Scenarios expose
  val createBundleScn: ScenarioBuilder = scenario("Test To Create Bundle")
    .exec(_createBundle())

  val listAllBundlesScn: ScenarioBuilder = scenario("Test To Fetch Bundles")
    .exec(_listAllBundles())

  val getOneBundleScn: ScenarioBuilder = scenario("Test To Get One Bundle")
    .doIf(session => session(objectIdKey).asOption[Int].isEmpty) {
      _listAllBundles()
    }
    .pause(5)
    .exec(_getOneBundle())

  val updateOneBundleScn: ScenarioBuilder = scenario("Test To Update One Bundle")
    .doIf(session => session(objectIdKey).asOption[Int].isEmpty) {
      _listAllBundles()
    }
    .pause(5)
    .exec(_updateOneBundle())

  val deleteOneBundleScn: ScenarioBuilder = scenario("Test To Delete One Bundle")
    .doIf(session => session(objectIdKey).asOption[Int].isEmpty) {
      _listAllBundles()
    }
    .pause(5)
    .exec(_deleteOneBundle())

}