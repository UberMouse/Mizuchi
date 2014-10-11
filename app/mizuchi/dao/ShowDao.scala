package mizuchi.dao

import play.api.db.slick.Session
import mizuchi.models.Show

trait ShowDao extends Dao {
  def all: Iterable[Show]
  def findById(id: Int): Option[Show]
  def insert(show: Show): Int
  def update(show: Show): Unit
}
