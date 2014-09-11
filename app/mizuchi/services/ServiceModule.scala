package mizuchi.services

import scaldi.Module

class ServiceModule extends Module {
  bind[ShowService] to injected[ShowServiceImpl]
  bind[ActionDispatcher] to injected[ActionDispatcherImpl]
}
