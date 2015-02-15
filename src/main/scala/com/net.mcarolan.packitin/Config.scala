package net.mcarolan.packitin

import scalaz._
import Scalaz._
import com.typesafe.config.{ Config => TypesafeConfig, ConfigFactory => TypesafeConfigFactory }
import net.mcarolan.packitin.util.TypesafeConfigUtil

case class DatabaseConfig(driverClass: String, jdbcUrl: String, username: String, password: String)
case class Config(databaseConfig: DatabaseConfig)

case object Config extends TypesafeConfigUtil {

  private[this] def readDatabaseConfig(config: TypesafeConfig): ValidationNel[String, DatabaseConfig] =
    (config.getStringValidationNel("database.driverClass") |@|
    config.getStringValidationNel("database.jdbcUrl") |@|
    config.getStringValidationNel("database.username") |@|
    config.getStringValidationNel("database.password"))(DatabaseConfig.apply)

  def read(config: TypesafeConfig = TypesafeConfigFactory.load()): ValidationNel[String, Config] =
    for {
      databaseConfig <- readDatabaseConfig(config)
    }
      yield Config(databaseConfig)

}
