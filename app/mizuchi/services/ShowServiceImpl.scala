package mizuchi.services

import play.api.db.slick.Session
import mizuchi.models.Show
import mizuchi.dao.ShowDao

class ShowServiceImpl(dao: ShowDao) extends ShowService {
  def list(implicit s: Session) = dao.all
  def create(show: Show)(implicit s: Session) = dao.insert(show)
}
