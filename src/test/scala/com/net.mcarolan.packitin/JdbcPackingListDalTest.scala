package net.mcarolan.packitin

import net.mcarolan.packitin.domain.PackingList
import org.specs2.mutable.Specification

import scala.slick.driver.H2Driver

class JdbcPackingListDalTest extends Specification with TestDatabase {

  "JdbcPackingListDal" should {
    "return all packing lists in the test data" in {
      val packingListDal = JdbcPackingListDal(H2Driver.profile, database)

      packingListDal.getPackingLists() must be_==(List(PackingList(1, "Skiing"), PackingList(2, "Snowboarding")))
    }
  }

}