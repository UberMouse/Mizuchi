package services

import play.api.db.slick.Session
import models.Show

trait ShowService {
  def list(implicit s: Session): Seq[Show]
}
