package utils

object Environment {
  // ::: bases urls
  val baseUrl: String            = scala.util.Properties.envOrElse("baseUrl", "http://localhost:8081/api/fhir")
  val binariesUrl: String        = scala.util.Properties.envOrElse("binariesUrl", "/binaries")
  val bundlesUrl: String         = scala.util.Properties.envOrElse("bundlesUrl", "/bundles")


  // ::: Default Resources values
  val resourceType_binary        = "Binary"
  val resourceType_bundle        = "Bundle"

  val meta_versionId             = "1"
  val meta_lastUpdated           = "2022-11-01T11:28:31.885+00:00"
  val meta_tag_system            = "http://hl7.org/fhir/composition-status"
  val meta_tag_code              = "preliminary 1"
  val meta_tag_display           = "default Binary object"


  // ::: Default Update Resources values
  val meta_versionId_update      = "2"
  val meta_tag_system_update     = "http://hl7.org/fhir/composition-updated-status"
  val meta_tag_code_update       = "update 1"
  val meta_tag_display_update    = "updated object"
}