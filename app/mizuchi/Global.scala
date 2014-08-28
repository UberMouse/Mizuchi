package mizuchi

import mizuchi.services.ServiceModule
import mizuchi.dao.DaoModule
import play.api.GlobalSettings

object Global extends GlobalSettings {
  def appModule = new DaoModule :: new ServiceModule
}
