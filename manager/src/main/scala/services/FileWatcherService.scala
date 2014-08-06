package services

import akka.actor.{PoisonPill, ActorContext, Actor}
import services.Messages.FileWatcherService._
import java.nio.file._
import java.io.File
import scala.collection.immutable.HashMap
import services.Messages.FileWatcherService.Shutdown
import services.Messages.FileWatcherService.Monitor
import java.nio.file.attribute.BasicFileAttributes
import scala.collection.JavaConversions._

class FileWatcherService extends ServiceActor with Actor {
  import java.nio.file.StandardWatchEventKinds._

  var DirectoryKeys = new HashMap[WatchKey, Path]
  val WatchService = FileSystems.getDefault.newWatchService()
  var Watcher = new Watcher(WatchService)
  val WatchThread = new Thread(Watcher)
  WatchThread.start()
  def actorRefFactory: ActorContext = context

  def receive: Actor.Receive = {
    case Monitor(root) => {
      val key = root.register(WatchService, StandardWatchEventKinds.ENTRY_CREATE)
      DirectoryKeys += ((key, root))
    }
    case Shutdown() => {
      Watcher.dead = true
      self ! PoisonPill
    }
    case _ => println("Fallthrough in FileWatcherService")
  }

  class Watcher(ws: WatchService) extends Runnable {
    var dead = false
    def run() {
      while(!dead) {
        val key = ws.take()
        val events = key.pollEvents().map(_.asInstanceOf[WatchEvent[Path]]).toList

        for(e <- events) {
          process(e, key)
        }

        Thread.sleep(50)
      }
    }

    private def process(event: WatchEvent[Path], key: WatchKey) {
      event.kind() match {
        case ENTRY_CREATE => {
          val root = DirectoryKeys(key)
          val fullPath = root.resolve(event.context())
          if(Files.isRegularFile(fullPath)) {
            context.parent ! NewFile(fullPath.toFile)
          }
          else {
            Files.walkFileTree(fullPath, new SimpleFileVisitor[Path]() {
              override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes) = {
                self ! Monitor(dir)
                FileVisitResult.CONTINUE
              }
            })
          }
          key.reset()
        }
      }
    }
  }
}
