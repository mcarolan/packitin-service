package net.mcarolan.packitin

import argonaut._
import Argonaut._
import spray.http.HttpEntity
import spray.http.MediaTypes._
import spray.httpx.marshalling.Marshaller

trait SprayArgonautSupport {

  implicit def JsonMarshaller: Marshaller[Json] =
    Marshaller.of[Json](`application/json`) { (value, _, ctx) =>
      val stringValue = value.spaces4
      ctx.marshalTo(HttpEntity(`application/json`, stringValue))
    }

  implicit def EncodeJsonMarshaller[T](implicit ev: EncodeJson[T]): Marshaller[T] =
    Marshaller.of[T](`application/json`) { (value, _, ctx) =>
      val jsonValue = ev(value)
      JsonMarshaller(jsonValue, ctx)
    }

}