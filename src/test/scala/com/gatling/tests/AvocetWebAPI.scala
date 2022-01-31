package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AvocetWebAPI extends Simulation {

  val httpProtocol = http
    .baseUrl("https://avocet-app-poc.eastus.cloudapp.azure.com/WebApi/avocet/v1")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en-GB;q=0.9,en;q=0.8")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36")


  val scn = scenario("Login with AppID")
    .exec(http("GetApps")
      .get("/Authentication/Apps")
      .check(jsonPath("$[0].Id").saveAs("appidpara")))

    .exec(http("login using appid")
      .get("/Authentication/Login/${appidpara}"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}