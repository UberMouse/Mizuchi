package services

import models.Show
import play.api.db.slick.Session

class ShowServiceImpl extends ShowService {
  def list(implicit s: Session): Seq[Show] = null
}
