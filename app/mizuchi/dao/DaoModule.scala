package mizuchi.dao

import scaldi.Module

class DaoModule extends Module {
  bind[ShowDao] to injected[ShowDaoImpl]
  bind[SeasonMappingDao] to injected[SeasonMappingDaoImpl]
}
