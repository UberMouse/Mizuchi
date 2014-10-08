package mizuchi.sync

import scaldi.Module
import mizuchi.sync.actions.CreateShow

class SyncModule extends Module {
  bind[ActionDispatcher] to injected[ActionDispatcherImpl]
}
