package net.mcarolan.packitin

import com.googlecode.flyway.core.Flyway

object FlywayMigrations {

  def migrateToLatest(config: DatabaseConfig): Unit = {
    val flyway = new Flyway()
    flyway.setDataSource(config.jdbcUrl, config.username, config.password)
    flyway.migrate()
  }

}