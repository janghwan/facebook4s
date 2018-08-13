package com.github.vooolll.client

import java.net.URL

import com.github.vooolll.base.FacebookClientSupport
import cats.implicits._
import com.github.vooolll.client.feed._
import com.github.vooolll.domain.FacebookPaging
import com.github.vooolll.domain.friends.{FacebookFriends, FacebookFriendsSummary}
import com.github.vooolll.domain.profile._

class FriendsSpec extends FacebookClientSupport {

  import com.github.vooolll.base.TestConfiguration._

  val friendId = FacebookUserId("595040754173861")

  val friend = FacebookUser(
    id = friendId,
    email = None,
    name = "Valeriy Baibossynov".some,
    picture = Some(FacebookUserPicture(50.0, isSilhouette = false, new URL("https://platform-lookaside.fbsbx.com/platform/profilepic/"), 50.0)),
    firstName = Some("Valeriy"),
    lastName = Some("Baibossynov"),
    link = Some(new URL("https://www.facebook.com")),
    gender = Some(Gender.Male),
    ageRange = None,
    hometown = Some(FacebookTown("115486911798138", "Almaty, Kazakhstan")),
    location = Some(FacebookTown("111536985531661", "Riga, Latvia")))

  val friendPaging = FacebookPaging(
    "QVFIUjlua0ltaGRGXzdlT295V25GX01ZAV1dJczhsNmxBSlBWcWRDS0RET2x1VXNmOXVGczhEZAzJDbmdSNFBRVURNZA0pES2hKRWZACblRBUlZA0ZAnl0YUJvVUd3".some,
    "QVFIUjlua0ltaGRGXzdlT295V25GX01ZAV1dJczhsNmxBSlBWcWRDS0RET2x1VXNmOXVGczhEZAzJDbmdSNFBRVURNZA0pES2hKRWZACblRBUlZA0ZAnl0YUJvVUd3".some)

  val friendsSummary = FacebookFriendsSummary(totalCount = 1)

  val facebookFriends = FacebookFriends(
    friends = List(friend),
    paging = friendPaging.some,
    summary = friendsSummary.some
  )

  "Graph Api" should {
    "return user friends" in { c =>
      c.friends(FacebookUserId("me")).map(_.withoutQueryParams shouldBe facebookFriends)
    }

    "return user friends result" in { c =>
      c.friendsResult(FacebookUserId("me")).map(_.map(_.withoutQueryParams) shouldBe facebookFriends.asRight)
    }
  }
}
