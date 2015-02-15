package net.mcarolan.packitin

import com.typesafe.config.ConfigFactory
import org.specs2.mutable.Specification
import org.specs2.scalaz.ScalazMatchers

import scalaz.NonEmptyList

class ConfigSpec extends Specification with ScalazMatchers {

  "Config" should {

    "return a success when created with a valid config" in {
      val configString =
        """
          |database {
          | driverClass = mysql
          | jdbcUrl = localhost
          | username = blah
          | password = bleh
          |}
        """.stripMargin

      Config.read(ConfigFactory.parseString(configString)) must beSuccessful
    }

    "return a failure when created with config missing a username and password. username and password must be included in the failure reasons" in {
      val configString =
        """
          |database {
          | driverClass = mysql
          | jdbcUrl = localhost
          |}
        """.stripMargin

      val result = Config.read(ConfigFactory.parseString(configString))

      result must beFailing.like {
        case l: NonEmptyList[String] => {
          (l.list must containMatch("username")) and
            (l.list must containMatch("password"))
        }
      }
    }

  }
}
