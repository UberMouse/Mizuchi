package mizuchi.models

import play.api.libs.json._

case class ActionResult(id: String, success: Boolean, args: Option[String] = None)

object ActionResult {
  implicit def ActionResultFormat = Json.format[ActionResult]
}

