package mizuchi.sync.actions

import scaldi.{ Injectable, Injector }
import mizuchi.models.{ ActionResult, Action }
import nz.ubermouse.hummingbird.{Manga, Anime, Hummingbird}
import play.api.libs.json.Json
import muster.codec.play.PlayJsonCodec
import muster.codec.play._

class SearchHummingbird(implicit inj: Injector) extends ActionHandler("SEARCH_HUMMINGBIRD") with Injectable {
  val hummingbird = inject[Hummingbird]

  def process(action: Action): ActionResult = {
    var args: SearchHummingbirdArgs = null
    try {
      args = PlayJsonCodec.as[SearchHummingbirdArgs](Json.parse(action.args))
    } catch {
      case e => e.printStackTrace()
    }
    //todo fix
    val result = hummingbird.search(args.search).flatMap {
      case x: Manga => None
      case x: Anime => Option(x)
    }.map(x => SearchResult(x.canonical_title, s"http://hummingbird.me/anime/${x.slug}", x.poster_image))
    ActionResult(action.id, success = true, Option(PlayJsonCodec.from(SearchHummingbirdResponse(result)).toString()))
  }
}

case class SearchHummingbirdArgs(search: String)
object SearchHummingbirdArgs {
  implicit def format = Json.format[SearchHummingbirdArgs]
}
case class SearchResult(name: String, show_url: String, poster_url: String)
object SearchResult  {
  implicit def format = Json.format[SearchResult]
}
case class SearchHummingbirdResponse(results: Iterable[SearchResult])
object SearchHummingbirdResponse  {
  implicit def format = Json.format[SearchHummingbirdResponse]
}
