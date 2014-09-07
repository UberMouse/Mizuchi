package mizuchi.controllers

import scaldi.Module

class ControllerModule extends Module {
  binding to new ShowController
  binding to new WebsocketController
}
