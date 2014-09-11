package mizuchi.tests.services

import mizuchi.tests.UnitSpec
import scaldi.{Injectable, Module}
import mizuchi.services.{ActionDispatcherImpl, ActionHandler, ActionDispatcher}
import mizuchi.models.{ActionResult, Action}
import scala.concurrent.{Future, Await}
import concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class ActionDispatcherSpec extends UnitSpec  with Injectable {
  class DummyHandler extends ActionHandler {
    def apply(action: Action): Future[ActionResult] = Future {
      inject[ActionResult]
    }
  }

  implicit val module = new Module {
    bind[ActionDispatcher] toProvider injected[ActionDispatcherImpl]
    bind[Action] to Action("1", "test", Map("foo" -> "bar"))
    bind[ActionHandler] toProvider new DummyHandler
    bind[ActionResult] toProvider ActionResult(success = true)
  } :: mizuchi.Global.applicationModule

  describe("Action Dispatcher") {
    it("works") {
      val dispatcher = inject[ActionDispatcher]
      val handler = inject[ActionHandler]
      val action = inject[Action]
      val actionResult = inject[ActionResult]

      dispatcher.registerActionHandler("test", handler)
      val result = dispatcher(action).get

      Await.result(result, 5.seconds) mustEqual actionResult
    }

    describe("Handler Registration") {
      it("returns Success on a successful registration") {
        val dispatcher = inject[ActionDispatcher]
        val handler = inject[ActionHandler]

        val result = dispatcher.registerActionHandler("test", handler)

        result.isSuccess mustEqual true
      }
    }
  }
}
