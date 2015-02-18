package net.mcarolan.packitin

import net.mcarolan.packitin.domain.PackingList

trait PackingListDal {

  def getPackingLists(): List[PackingList]

}