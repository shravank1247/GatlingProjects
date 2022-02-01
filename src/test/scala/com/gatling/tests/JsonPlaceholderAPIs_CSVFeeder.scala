package com.gatling.tests
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class JsonPlaceholderAPIs_CSVFeeder extends Simulation{

  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com/posts")
    .header("Accept", "application/json")
    .proxy(Proxy("localhost",8888)) //if fiddler is configured as proxy

  val csvFeeder = csv("data/JsonPlaceholderAPI_csvFeeder.csv").circular


  def getAResource() = {
    repeat(10) {
      feed(csvFeeder)
      .exec(http("GetAResource")
        .get("/${id}")
        .check(jsonPath("$.userId").is("${userId}")))
    }
  }

  val scn1 = scenario("using csv feeder code for JosnPlaceholderAPIs")
    .exec(getAResource())

  setUp(scn1.inject(atOnceUsers(1))).protocols(httpProtocol)
}
