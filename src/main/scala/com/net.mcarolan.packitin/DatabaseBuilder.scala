package net.mcarolan.packitin

import scala.slick.jdbc.JdbcBackend

case object DatabaseBuilder {

  def apply(config: DatabaseConfig): Database = {
    JdbcBackend.backend.Database.forURL(config.jdbcUrl, config.username, config.password, driver = config.driverClass)
  }

}