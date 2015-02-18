package net.mcarolan.packitin

import argonaut._
import Argonaut._

package object domain {

  case class PackingList(id: Int, name: String)

  case class PackingListResponse(packingLists: List[PackingList])

  object PackingList {

    implicit val PackingListCodecJson: CodecJson[PackingList] =
      casecodec2(PackingList.apply, PackingList.unapply)("id", "name")

  }

  object PackingListResponse {

    implicit val PackingListResponseCodecJson: CodecJson[PackingListResponse] =
      casecodec1(PackingListResponse.apply, PackingListResponse.unapply)("packingLists")

  }

}