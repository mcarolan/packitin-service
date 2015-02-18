package net.mcarolan.packitin

import net.mcarolan.packitin.domain.PackingList

import scala.slick.driver.JdbcProfile

case class JdbcPackingListDal(profile: JdbcProfile, database: Database) extends PackingListDal {

  import profile.simple._

  private class PackingLists(tag: Tag) extends Table[(Int, String)](tag, "packinglist") {

    def id = column[Int]("id", O.PrimaryKey)

    def name = column[String]("name")

    def * = (id, name)
  }

  private val packingLists = TableQuery[PackingLists]

  override def getPackingLists(): List[PackingList] = database withSession { implicit session =>
    packingLists.list.map((PackingList.apply _).tupled)
  }

}