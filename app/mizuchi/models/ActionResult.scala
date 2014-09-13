package mizuchi.models

import play.api.libs.json._

case class ActionResult(actionId: String, success: Boolean, message: Option[String] = None)

object ActionResult {
  implicit def ActionResultFormat = Json.format[ActionResult]
}

