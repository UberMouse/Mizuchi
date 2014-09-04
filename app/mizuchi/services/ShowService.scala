package mizuchi.services

import play.api.db.slick.Session
import mizuchi.models.Show

trait ShowService {
  def list(implicit s: Session): Seq[Show]
  def create(show: Show)(implicit s: Session): Int
}