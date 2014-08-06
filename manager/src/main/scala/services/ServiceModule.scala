package services

import scaldi.Module
import nz.ubermouse.processor.FileProcessor
import shows.ShowManager

class ServiceModule extends Module {
  bind[FileManagerService] toProvider new FileManagerService(injected[ShowManager])
  bind[ProcessingService] toProvider injected[ProcessingService]
  bind[FileWatcherService] toProvider injected[FileWatcherService]
  bind[ServiceActor] toProvider new FileManagerHttpService
}
