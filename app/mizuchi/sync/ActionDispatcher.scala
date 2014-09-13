package mizuchi.sync

import mizuchi.models.{ ActionResult, Action }
import scala.util.Try
import scala.concurrent.Future
import mizuchi.sync.actions.ActionHandler

trait ActionDispatcher {
  type ActionPublisher = (Action) => Try[Future[ActionResult]]

  def apply(action: Action): Try[Future[ActionResult]]
  def registerActionHandler(actionName: String, handler: ActionHandler): Try[ActionPublisher]
}

