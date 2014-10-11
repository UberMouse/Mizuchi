package mizuchi.sync.actions

import mizuchi.sync.{ ActionDispatcher }
import mizuchi.models.{ Show, ActionResult, Action }
import scala.concurrent.Future
import play.api.db.slick.DB
import mizuchi.dao.{ SeasonMappingDao, ShowDao }
import scaldi.{ Injector, Injectable }
import play.api.Play.current
import play.api.libs.json.Json
import muster.codec.play._

class CreateShow(implicit inj: Injector) extends ActionHandler("CREATE_SHOW") with Injectable {
  val showDao = inject[ShowDao]
  val mappingDao = inject[SeasonMappingDao]

  def process(action: Action): ActionResult = {
    var args: CreateShowArgs = null
    try {
      args = PlayJsonCodec.as[CreateShowArgs](Json.parse(action.args))
    } catch {
      case e => e.printStackTrace()
    }
    val showId = showDao insert Show(-1, args.name, args.hummingbirdId, args.tvdbId)
    args.tvdbSeason.foreach(season => mappingDao.create(args.tvdbId, Integer.valueOf(season)))

    ActionResult(action.id, success = true, Option(PlayJsonCodec.from(CreateShowResponse(showDao.findById(showId).get)).toString()))
  }
}

case class CreateShowArgs(name: String, tvdbId: String, hummingbirdId: String, tvdbSeason: Option[String])

object CreateShowArgs {
  implicit def format = Json.format[CreateShowArgs]
}

case class CreateShowResponse(show: Show)

object CreateShowResponse {
  implicit def format = Json.format[CreateShowResponse]
}