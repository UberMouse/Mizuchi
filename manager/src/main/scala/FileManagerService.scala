import akka.actor.{ActorContext, Actor}
import java.io.File
import nz.ubermouse.anime.AnimeProcessor
import shapeless.get
import shows.{Show, ShowManager}
import spray.routing._
import spray.http._
import MediaTypes._

trait ServiceActor {
  def actorRefFactory: ActorContext

  def receive: Actor.Receive
}

class FileManagerServiceActor(processor: AnimeProcessor, showManager: ShowManager) extends Actor with ServiceActor with HttpService {

  def actorRefFactory = context

  def receive = runRoute(routing)

  val routing = {
    path("forceProcess") {
      get {
        complete {
          val processFrom = new File("""G:\Downloads\Deluge\Completed\Anime""")
          val processTo = new File("""G:\Videos\Anime""")
          val showTitles = showManager.all.map(_.name)

          processor.process(processFrom, processTo, showTitles)
          StatusCodes.OK
        }
      }
    } ~
    path("shows") {
      get {
        parameters('showName) {showName =>
          showManager.add(showName)
          redirect("/", StatusCodes.TemporaryRedirect)
        }
      }
    } ~
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            html.index.render(showManager.all).body
          }
        }
      }
    }
  }

}

