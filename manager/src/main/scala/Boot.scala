import akka.actor.{ActorSystem, Props}
import akka.io.IO
import database.DatabaseModule
import nz.ubermouse.anime.ProcessorModule
import scaldi.akka.AkkaInjectable
import scaldi.Module
import shows.ShowModule
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

class AkkaModule extends Module {
  bind [ActorSystem] to ActorSystem("web-responders") destroyWith(_.shutdown())
}

class FileManagerModule extends Module {
  bind [ServiceActor] toProvider injected [FileManagerServiceActor]
}

object Boot extends App with AkkaInjectable {
  implicit val appModule = new FileManagerModule :: new AkkaModule :: new DatabaseModule :: new ShowModule :: new ProcessorModule

  implicit val system = inject [ActorSystem]

  val service = injectActorRef [ServiceActor]

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ? Http.Bind(service, interface = "localhost", port = 777)
}