package utils

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol._
import utils.Environment._

object Headers {
  val acceptHeader: String                  = scala.util.Properties.envOrElse("Accept", "*/*")
  val acceptEncodingHeader: String          = scala.util.Properties.envOrElse("Accept-Encoding", "gzip, deflate, br")
  val acceptLanguageHeader: String          = scala.util.Properties.envOrElse("Accept-LanguageHeader", "de-DE,de;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5")
  val upgradeInsecureRequestsHeader: String = scala.util.Properties.envOrElse("Upgrade-Insecure-Requests", "1")
  val userAgentHeader: String               = scala.util.Properties.envOrElse("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36")
  val contentTypeAppJson: String            = scala.util.Properties.envOrElse("Content-Type", "application/json")


  val commonHeaders: Map[String, String] = Map.apply(
    "Accept"                    -> "*/*",
    "Accept-Encoding"           -> "gzip, deflate, br",
    "Accept-LanguageHeader"     -> "de-DE,de;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5",
    "Upgrade-Insecure-Requests" -> "1",
    "User-Agent"                -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36"
  )


  // ::: Http Conf
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(baseUrl)
    .headers(commonHeaders)
    .contentTypeHeader(contentTypeAppJson)

}