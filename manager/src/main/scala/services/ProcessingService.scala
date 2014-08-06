package services

import akka.actor.{ActorContext, Actor}
import nz.ubermouse.processor.FileProcessor

class ProcessingService(processor: FileProcessor) extends ServiceActor with Actor {
  import Messages.ProcessingService._
  def actorRefFactory: ActorContext = context

  def receive: Actor.Receive = {
    case ProcessDirectory(root, to, titles) => processor.process(root, to, titles)
    case ProcessFile(file, to, titles) => {
      processor.process(file, to, titles)
    }
  }
}
