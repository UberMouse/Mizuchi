package services

import akka.actor.{ActorContext, Actor}
import java.io.File
import nz.ubermouse.processor.FileProcessor
import shapeless.get
import shows.{Show, ShowManager}
import spray.routing._
import spray.http._
import MediaTypes._
import scaldi.akka.AkkaInjectable
import scaldi.Injector
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.util.Timeout


class FileManagerHttpService(implicit inj: Injector) extends Actor with ServiceActor with HttpService with AkkaInjectable {
  import Messages.FileManagerService._
  implicit val timeout = Timeout(1.seconds)
  val manager = injectActorRef[FileManagerService]

  val routing = {
    path("forceProcess") {
      get {
        complete {
          val processFrom = new File("""G:\Downloads\Deluge\Completed\Anime""")

          manager ! ProcessDirectoryRequest(processFrom)

          StatusCodes.OK
        }
      }
    } ~
    path("shows") {
      get {
        parameters('showName) {showName =>
          val show = Show(showName)

          manager ! AddShow(show)

          redirect("/", StatusCodes.TemporaryRedirect)
        }
      }
    } ~
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            val response = Await.result(manager.ask(RequestShows()).mapTo[ShowsResponse], timeout.duration)
            html.index.render(response.shows).body
          }
        }
      }
    }
  }

  def actorRefFactory = context

  def receive = runRoute(routing)
}

