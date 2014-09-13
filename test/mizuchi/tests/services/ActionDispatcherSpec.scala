package mizuchi.tests.services

import mizuchi.tests.UnitSpec
import scaldi.{ Injectable, Module }
import mizuchi.models.{ ActionResult, Action }
import scala.concurrent.{ Future, Await }
import concurrent.duration._
import mizuchi.sync.{ ActionDispatcherImpl, ActionDispatcher }
import mizuchi.sync.actions.{ CreateShowArgs, ActionHandler }
import play.api.libs.json.Json
import muster.codec.play._

class ActionDispatcherSpec extends UnitSpec with Injectable {
  class DummyHandler extends ActionHandler("test") {
    def process(action: Action): ActionResult = {
      val args = PlayJsonCodec.as[DummyArgs](Json.parse(action.args))
      if (args.name == "bar")
        inject[ActionResult]('success)
      else
        inject[ActionResult]('failure)
    }
  }

  case class DummyArgs(name: String)
  object DummyArgs {
    implicit def format = Json.format[DummyArgs]
  }

  implicit val module = new Module {
    bind[ActionDispatcher] toProvider injected[ActionDispatcherImpl]
    bind[Action] identifiedBy 'success to Action("1", "test", """{"name": "bar"}""")
    bind[Action] identifiedBy 'failure to Action("1", "test", """{"name": "foo"}""")
    bind[ActionResult] identifiedBy 'success to ActionResult("1", success = true)
    bind[ActionResult] identifiedBy 'failure to ActionResult("1", success = false)
  } :: mizuchi.Global.applicationModule

  describe("Action Dispatcher") {
    it("works") {
      val dispatcher = inject[ActionDispatcher]
      val handler = new DummyHandler
      val action = inject[Action]('success)
      val actionResult = inject[ActionResult]('success)

      dispatcher.registerActionHandler("test", handler)
      val result = dispatcher(action).get

      Await.result(result, 5.seconds) mustEqual actionResult
    }

    it("parses arguments") {
      val dispatcher = inject[ActionDispatcher]
      val handler = new DummyHandler
      val action = inject[Action]('failure)
      val actionResult = inject[ActionResult]('failure)

      dispatcher.registerActionHandler("test", handler)
      val result = dispatcher(action).get

      Await.result(result, 5.seconds) mustEqual actionResult
    }

    describe("Handler Registration") {
      it("returns Success on a successful registration") {
        val dispatcher = inject[ActionDispatcher]
        val handler = new DummyHandler

        val result = dispatcher.registerActionHandler("test", handler)

        result.isSuccess mustEqual true
      }
    }
  }
}
