package mizuchi.sync.actions

import mizuchi.models.{ ActionResult, Action }
import scala.concurrent.Future
import scaldi.{ Injectable, Injector }
import mizuchi.sync.{ ActionDispatcherImpl, ActionDispatcher }
import scala.reflect.runtime.universe._
import scala.util.Success
import scala.util.Failure
import muster.codec.play._
import muster._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global
import muster.ast.AstNode

trait ActionHandlerArgs

abstract class ActionHandler(actionName: String)(implicit inj: Injector) extends Injectable {
  val dispatcher = inject[ActionDispatcher]
  val publish = {
    dispatcher.registerActionHandler(actionName, this) match {
      case Success(publisher) => publisher
      case Failure(ex) => println(s"Error registering ActionHandler for $actionName: ${ex.getMessage}")
    }
  }

  def apply(action: Action): Future[ActionResult] = Future {
    process(action)
  }

  def process(action: Action): ActionResult
}
