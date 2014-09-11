package mizuchi.models

import play.api.libs.json._

case class Action(id: String, action_name: String, args: Map[String, String])

object Action {
  implicit def ActionFormat = Json.format[Action]
}
