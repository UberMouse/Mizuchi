package mizuchi.services

import play.api.db.slick.Session
import mizuchi.models.Show
import mizuchi.dao.ShowDao

class ShowServiceImpl(dao: ShowDao) extends ShowService {
  def list(implicit s: Session): Seq[Show] = null
}
