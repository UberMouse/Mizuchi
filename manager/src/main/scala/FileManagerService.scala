import akka.actor.Actor
import octopus._
import spray.routing._
import spray.http._
import MediaTypes._

class FileManagerServiceActor extends Actor with FileManagerService {

  def actorRefFactory = context

  def receive = runRoute(routing)
}

trait FileManagerService extends HttpService {

  val routing = {
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