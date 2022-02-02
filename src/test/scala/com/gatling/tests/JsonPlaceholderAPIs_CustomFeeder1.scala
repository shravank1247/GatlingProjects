package com.gatling.tests
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class JsonPlaceholderAPIs_CustomFeeder1 extends Simulation{

  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com/posts")
    .header("Accept", "application/json")
    .proxy(Proxy("localhost",8888)) //if fiddler is configured as proxy

  val idNumber = (1 to 5).iterator
  val customFeeder = Iterator.continually(Map("id" -> idNumber.next()))

  def getAResource() = {
    repeat(5) {
      feed(customFeeder)
        .exec(http("GetAResource")
          .get("/${id}")
          .check(status.is(200)))
    }
  }

  val scn1 = scenario("using custom feeder code for JosnPlaceholderAPIs")
    .exec(getAResource())

  setUp(scn1.inject(atOnceUsers(1))).protocols(httpProtocol)
}
