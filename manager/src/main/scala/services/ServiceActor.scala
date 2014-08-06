package services

import akka.actor.{Actor, ActorContext}

trait ServiceActor {
  def actorRefFactory: ActorContext

  def receive: Actor.Receive
}
