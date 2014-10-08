package mizuchi.sync.actions

import scaldi.{ Injectable, Injector }
import mizuchi.models.{ ActionResult, Action }

class Initialize(implicit inj: Injector) extends ActionHandler("INIT") with Injectable {
  def process(action: Action): ActionResult = ActionResult(action.id, success = true)
}
