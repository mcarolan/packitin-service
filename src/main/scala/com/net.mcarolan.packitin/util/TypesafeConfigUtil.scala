package net.mcarolan.packitin.util

import scala.util.{Failure, Success, Try}
import scalaz.{Success => _, Failure => _, _}
import Scalaz._
import net.mcarolan.packitin._

trait TypesafeConfigUtil {

  implicit class TypesafeConfigExtensions(val config: TypesafeConfig) {

     def getStringValidationNel(path: String): ValidationNel[String, String] = {
       Try(config.getString(path)) match {
         case Success(value) => value.successNel[String]

         case Failure(reason) => reason.getMessage.failureNel[String]
       }
     }

  }

}