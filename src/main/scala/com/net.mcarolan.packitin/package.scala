package net.mcarolan

import scala.slick.jdbc.JdbcBackend

package object packitin {

  type TypesafeConfig = com.typesafe.config.Config
  type Database = JdbcBackend.backend.DatabaseDef

}