package services

import java.io.File
import shows.Show

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
}
