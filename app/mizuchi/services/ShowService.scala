package mizuchi.services

import play.api.db.slick.Session
import mizuchi.models.Show

trait ShowService {
  def list(implicit s: Session): Seq[Show]
}
