package mizuchi.dao

import play.api.db.slick.Session
import mizuchi.models.Show

trait ShowDao {
  def all(implicit s: Session): Seq[Show]
  def findById(id: Int)(implicit s: Session): Option[Show]
  def insert(show: Show)(implicit s: Session): Int
  def update(show: Show)(implicit s: Session): Unit
}
