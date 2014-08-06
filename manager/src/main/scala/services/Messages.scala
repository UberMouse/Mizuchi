package services

import java.io.File
import shows.Show
import java.nio.file.Path

object Messages {
  object FileManagerService {
    case class ProcessDirectoryRequest(root: File)
    case class ProcessFileRequest(file: File)
    case class AddShow(show: Show)
    case class RequestShows()
    case class ShowsResponse(shows: Iterable[Show])
  }

  object ProcessingService {
    case class ProcessDirectory(root: File, to: File, titles: Iterable[String])
    case class ProcessFile(file: File, to: File, titles: Iterable[String])
  }

  object FileWatcherService {
    case class Monitor(root: Path)
    case class Shutdown()
    case class NewFile(file: File)
  }
}
