package net.mcarolan.packitin

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.slick.driver.MySQLDriver

import scalaz._
import Scalaz._

object Boot extends App {

  Config.read() match {

    case Success(config) => start(config)

    case Failure(reasons) => {
      val reasonsPretty = reasons.list.toSet.mkString("\n + ")
      throw new IllegalStateException(s"Could not read config because: $reasonsPretty")
    }

  }

  def start(config: Config) {
    // we need an ActorSystem to host our application in
    implicit val system = ActorSystem("on-spray-can")

    val database = DatabaseBuilder(config.database)
    val packingListDal: PackingListDal = JdbcPackingListDal(MySQLDriver, database)
    val packingListService = system.actorOf(PackingListServiceActor.props(packingListDal))

    // flyway migrations
    val appliedMigrations = FlywayMigrations.migrateToLatest(config.database)

    implicit val timeout = Timeout(5.seconds)
    // start a new HTTP server on port 8080 with our service actor as the handler
    IO(Http) ? Http.Bind(packingListService, interface = "localhost", port = 8080)
  }

}
