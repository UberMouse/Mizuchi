package mizuchi

import mizuchi.services.ServiceModule
import mizuchi.dao.DaoModule
import play.api.{ Application, GlobalSettings }
import scaldi.play.ScaldiSupport
import scaldi.{ Module, Injector }
import mizuchi.controllers.ControllerModule
import mizuchi.sync.SyncModule

object Global extends GlobalSettings with ScaldiSupport {
  def applicationModule: Injector = new DaoModule :: new ServiceModule :: new ControllerModule :: new SyncModule
}
