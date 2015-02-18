package net.mcarolan.packitin

import akka.actor.ActorRefFactory
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import argonaut._
import Argonaut._
import net.mcarolan.packitin.domain._
import spray.http._

import scala.slick.driver.H2Driver

class PackingListServiceTest extends Specification with Specs2RouteTest with PackingListService with TestDatabase {
  def actorRefFactory: ActorRefFactory = system

  val packingListDal = JdbcPackingListDal(H2Driver.profile, database)

  "ListService" should {

    "return the list of packing lists" in {
      Get("/packing-list") ~> listRoute ~> check {
        response.entity.asString.decodeOption[PackingListResponse] === Some(PackingListResponse(List(PackingList(1, "Skiing"), PackingList(2, "Snowboarding"))))
      }
    }

  }

}