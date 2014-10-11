package mizuchi.sync.actions

import scaldi.{ Injectable, Injector }
import mizuchi.models.{ Show, ActionResult, Action }
import mizuchi.services.ShowService
import play.api.libs.json.Json
import play.api.db.slick._
import muster.codec.play.PlayJsonCodec
import muster.codec.play._
import play.api.Play.current

class Initialize(implicit inj: Injector) extends ActionHandler("INIT") with Injectable {
  val shows = inject[ShowService]

  def process(action: Action): ActionResult = {
    val args = DB.withSession { implicit s =>
      InitializeArgs(shows.list.toList)
    }
    ActionResult(action.id, success = true, Option(PlayJsonCodec.from(args).toString()))
  }
}

case class InitializeArgs(shows: List[Show])

object InitializeArgs {
  implicit def format = Json.format[InitializeArgs]
}
