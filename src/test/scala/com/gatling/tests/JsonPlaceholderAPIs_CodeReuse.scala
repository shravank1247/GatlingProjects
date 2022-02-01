package com.gatling.tests
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class JsonPlaceholderAPIs_CodeReuse extends Simulation{

  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com/posts")
    .header("Content-type", "application/json")

  def getAResource() ={
    exec(http("GetAResource")
      .get("/1")
      .check(jsonPath("$.id").is("1"))
      .check(bodyString.saveAs("responseBody")))
      .exec { session => println(session("responseBody").as[String]); session} // used for debugging
  }

  def getAllResource() = {
    repeat(5) {   //repeating or looping http calls
      exec(http("GetAllResources")
        .get("/")
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("id")))
        .exec { session => println(session); session} //used for debugging session variable
    }
  }

  def createAResource() = {
    exec(http("PostAResource")
      .post("?title=123&body=123&userId=10")
      //.formParam("title","test123")
      //.formParam("body","test123body")
      //.formParam("userId","10")
      .check(status.in(201 to 210))
      .check(jsonPath("$.id").is("101")))
  }

  def updateAResource() = {
    exec(http("UpdateAResource")
      .put("/100?title=123&body=123&userId=1")
      .check(status.is(200))
      .check(jsonPath("$.id").is("100")))
  }

  def deletingAResource() = {
    exec(http("DeletingAResource")
      .delete("/100")
      .check(status.is(200)))
  }

  val scn1 = scenario("using reusable code for JosnPlaceholderAPIs")
    .exec(getAResource())
    .pause(1)
    .exec(getAllResource())
    .pause(1,1)
    .exec(createAResource())
    .pause(3)
    .exec(updateAResource())
    .pause(1)
    .exec(deletingAResource())

  setUp(scn1.inject(atOnceUsers(1))).protocols(httpProtocol)
}
