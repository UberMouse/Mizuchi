package mizuchi.models

import play.api.libs.json._

case class ActionResult(success: Boolean, message: Option[String] = None)

object ActionResult {
  implicit def ActionResultFormat = Json.format[ActionResult]
}


