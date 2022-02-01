package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.commons.validation._
import io.gatling.http.response.Response

class JsonPlaceholderAPIs extends Simulation {

  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com/posts")
    .header("Content-type", "application/json")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en-GB;q=0.9,en;q=0.8")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36")


  val scn1 = scenario("Sample GET calls for resources")
    .exec(http("GetAResource")
      .get("/1")
      .check(jsonPath("$.id").is("1"))
      .check(jsonPath("$.title").is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))

    .exec(http("GetAllResources")
      .get("/")
      .check(status.is(200)))
      //.check(jsonPath("$.[99]").find.exists))

  val scn2 = scenario("Create/Update/Delete a resource")
    .exec(http("PostAResource")
      .post("")
      .formParam("title","test123")
      .formParam("body","test123body")
      .formParam("userId","10")
      .check(status.is("201"))
      .check(jsonPath("$.id").is("101")))

    .exec(http("UpdateAResource")
      .put("/100")
      .formParam("title","test1")
      .formParam("body","test1")
      .formParam("userId","1")
      .check(status.is("200"))
      .check(jsonPath("$.id").is("100").saveAs("postedId")))

    .exec(http("GetAResourceUpdated")
      .get("")
      .formParam("id","${postedId}")
      .check(jsonPath("$[0].id").is("100")))

    .exec(http("DeletingAResource")
      .delete("/100")
      .check(status.is("200")))

  setUp(scn1.inject(atOnceUsers(1)),scn2.inject(atOnceUsers(1))).protocols(httpProtocol)

}