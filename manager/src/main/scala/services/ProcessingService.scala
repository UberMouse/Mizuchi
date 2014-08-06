package services

import akka.actor.{ActorContext, Actor}
import nz.ubermouse.processor.FileProcessor

class ProcessingService(processor: FileProcessor) extends ServiceActor with Actor {
  import Messages.ProcessingService._
  def actorRefFactory: ActorContext = context

  def receive: Actor.Receive = {
    case ProcessDirectory(root, to, titles) => processor.process(root, to, titles)
    case ProcessFile(_, _, _) => throw new NotImplementedError("ProcessFile is not implemented yet")
  }
}
