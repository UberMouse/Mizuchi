package shows

import scaldi.Module

class ShowModule extends Module {
  bind [ShowManager] to injected [ShowManager]
}
