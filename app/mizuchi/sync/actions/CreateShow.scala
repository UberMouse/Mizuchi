package mizuchi.sync.actions

import mizuchi.sync.{ ActionDispatcher }
import mizuchi.models.{ Show, ActionResult, Action }
import scala.concurrent.Future
import play.api.db.slick.DB
import mizuchi.dao.ShowDao
import scaldi.{ Injector, Injectable }
import play.api.Play.current
import play.api.libs.json.Json
import muster.codec.play._

class CreateShow(implicit inj: Injector) extends ActionHandler("CREATE_SHOW") with Injectable {
  val showDao = inject[ShowDao]

  def process(action: Action): ActionResult = {
    var args: CreateShowArgs = null
    try {
      args = PlayJsonCodec.as[CreateShowArgs](Json.parse(action.args))
    } catch {
      case e => e.printStackTrace()
    }
    DB.withSession { implicit s =>
      val name = args.show.name
      val show = Show(-1, name, "1", "1")

      showDao insert show
    }
    ActionResult(action.id, success = true)
  }
}

case class CreateShowArgs(show: Show)

object CreateShowArgs {
  implicit def format = Json.format[CreateShowArgs]
}