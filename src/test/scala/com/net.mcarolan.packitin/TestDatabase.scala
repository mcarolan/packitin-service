package net.mcarolan.packitin

import java.util.UUID

import scala.io.Source
import scala.slick.jdbc.StaticQuery

trait TestDatabase {

  private[this] val config =
     DatabaseConfig("org.h2.Driver", s"jdbc:h2:mem:${UUID.randomUUID()};DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false", "H2", "H2")

  FlywayMigrations.migrateToLatest(config)

  val database: Database = DatabaseBuilder(config)

  val testData = Source.fromURL(getClass.getResource("/test_data.sql")).getLines()

  database withSession { implicit session =>
    testData foreach { line =>
      StaticQuery.updateNA(line).execute
    }
  }

}