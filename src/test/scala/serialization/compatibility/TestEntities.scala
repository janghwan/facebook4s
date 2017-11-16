package serialization.compatibility

import java.time.Instant

import cats.syntax.option._
import config.FacebookConstants
import domain.feed.{FacebookFeed, FacebookPaging, FacebookSimplePost}
import domain.oauth._
import FacebookConstants._

import scala.concurrent.duration._

object TestEntities {
  val userAccessToken = FacebookAccessToken(
    TokenValue("test token"), UserAccessToken("bearer", 5107587.seconds))

  val appAccessToken = FacebookAccessToken(
    TokenValue("1234567891011121|A6BCDEFiGASDFdB1_Zviht7lzxc"), AppAccessToken("bearer"))

  val clientCode = FacebookClientCode("test-test-test-test", "machine id".some)

  val facebookOauthError = FacebookOauthError(FacebookError("Invalid verification code format."))

  val feed = FacebookFeed(
    List(
      FacebookSimplePost("499313270413277_504668796544391", "Valeryi Baibossynov updated his profile picture.",
        toInstant("2017-10-01T13:43:05+0000")),
      FacebookSimplePost("499313270413277_139299253081349",
        "Valeryi Baibossynov added a life event from May 2, 1993: Born on May 2, 1993.",
        toInstant("1993-05-02T07:00:00+0000"))
    ),
    FacebookPaging("https://graph.facebook.com1".some, "https://graph.facebook.com".some))


  def toInstant(string: String) = dateFormat.parse(string, Instant.from(_))

}