package mizuchi.sync.actions

import scaldi.Module

class ActionModule extends Module {
  bind[CreateShow] toNonLazy new CreateShow
  bind[Initialize] toNonLazy new Initialize
  bind[SearchHummingbird] toNonLazy new SearchHummingbird()
}
