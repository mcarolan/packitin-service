package net.mcarolan.packitin

import org.specs2.mutable.Specification

class TestDatabaseSpec extends Specification with TestDatabase {

  object DataAccessLayer {

    import scala.slick.driver.H2Driver.profile.simple._


    case class SchemaVersion(tag: Tag) extends Table[(String)](tag, "schema_version") {

      def version = column[String]("version", O.PrimaryKey)

      def * = (version)

    }

    case class PackingList(tag: Tag) extends Table[(Int)](tag, "packinglist") {

      def id = column[Int]("id", O.PrimaryKey)

      def * = (id)
      
    }

    private[this] val schemaVersions = TableQuery[SchemaVersion]
    private[this] val lists = TableQuery[PackingList]

    def numberOfSchemaVersionRows(): Int = database withSession { implicit session =>
      schemaVersions.length.run
    }
    
    def numberOfListRows(): Int = database withSession { implicit session =>
      lists.length.run
    }
  }

  "TestDatabase" should {

    "Have at least 1 schema version" in {
      DataAccessLayer.numberOfSchemaVersionRows() must be_>=(1)
    }

    "Have at least 2 rows in the list table, inserted as test data" in {
      DataAccessLayer.numberOfListRows() must be_>=(2)
    }

  }
}