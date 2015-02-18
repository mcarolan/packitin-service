package net.mcarolan.packitin

import akka.actor.{Props, Actor}
import net.mcarolan.packitin.domain.PackingListResponse
import spray.routing.{Route, HttpService}

class PackingListServiceActor(val packingListDal: PackingListDal) extends PackingListService with Actor {

  def actorRefFactory = context

  def receive = runRoute(listRoute)

}

object PackingListServiceActor {

  def props(packingListDal: PackingListDal): Props = Props(new PackingListServiceActor(packingListDal))

}

trait PackingListService extends HttpService with SprayArgonautSupport {

  def packingListDal: PackingListDal

  val listRoute: Route =
    path("packing-list") {
      get {
        complete {
          PackingListResponse(packingListDal.getPackingLists())
        }
      }
    }

}