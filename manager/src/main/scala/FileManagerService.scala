import akka.actor.{ActorContext, Actor}
import java.io.File
import nz.ubermouse.anime.AnimeProcessor
import octopus._
import shapeless.get
import shows.ShowManager
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
        }
      }
    } ~
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <h1>SUP</h1>
          }
        }
      }
    }
  }

}

