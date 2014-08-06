package services

import scaldi.akka.AkkaInjectable
import akka.actor.{ActorContext, Actor}
import scaldi.Injector
import nz.ubermouse.processor.FileProcessor
import shows.ShowManager
import services.Messages.FileManagerService.RequestShows
import java.io.File
import services.Messages.FileWatcherService.{NewFile, Monitor}

class FileManagerService(showManager: ShowManager)(implicit inj: Injector) extends ServiceActor with Actor with AkkaInjectable{
  import Messages.FileManagerService._
  import Messages.ProcessingService._
  val processor = injectActorRef[ProcessingService]
  val watcher = injectActorRef[FileWatcherService]

  watcher ! Monitor(new File( """G:\Downloads\Deluge\Completed\Anime""").toPath)

  def actorRefFactory: ActorContext = context

  def receive: Actor.Receive = {
    case ProcessDirectoryRequest(root) => {
      processor ! ProcessDirectory(root, destinationRoot, titles)
    }
    case ProcessFileRequest(file) => {
      processor ! ProcessFile(file, destinationRoot, titles)
    }
    case AddShow(show) => showManager.add(show)
    case RequestShows() => {
      sender ! ShowsResponse(showManager.all)
    }
    case NewFile(file) => self ! ProcessFileRequest(file)
    case _ => println("Fallthrough FileManager")
  }

  private def destinationRoot = new File("""G:\Videos\Anime""")
  private def titles = showManager.all.map(_.name)
}
