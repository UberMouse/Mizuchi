package mizuchi.services

import mizuchi.models.{Action, ActionResult}
import scala.concurrent.Future
import scala.util.Try

trait ActionDispatcher {
  type ActionPublisher = (Action) => Try[Future[ActionResult]]

  def apply(action: Action): Try[Future[ActionResult]]
  def registerActionHandler(actionName: String, handler: ActionHandler): Try[ActionPublisher]
}

trait ActionHandler {
  def apply(action: Action): Future[ActionResult]
}
