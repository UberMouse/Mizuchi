package mizuchi.services

import scala.util.{Failure, Success, Try}
import mizuchi.models.{ActionResult, Action}
import scala.concurrent.Future
import scala.collection.mutable

class NoActionHandlerRegisteredException extends Exception 
class ActionAlreadyHasHandlerRegisteredException extends Exception

class ActionDispatcherImpl extends ActionDispatcher {
  val handlers_mapping = mutable.HashMap[String, ActionHandler]()

  def apply(action: Action): Try[Future[ActionResult]] = {
    val handler = handlers_mapping.get(action.action_name).getOrElse(null)
    if(handler == null) Failure(new NoActionHandlerRegisteredException)
    Success[Future[ActionResult]](handler(action))
  }

  def registerActionHandler(actionName: String, handler: ActionHandler): Try[ActionPublisher] = {
    if(handlers_mapping.contains(actionName)) Failure(new ActionAlreadyHasHandlerRegisteredException)
    else {
      handlers_mapping += ((actionName, handler))
      Success[ActionPublisher](apply)
    }
  }
}
