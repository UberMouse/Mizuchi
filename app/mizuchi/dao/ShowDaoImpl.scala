package mizuchi.dao

import play.api.db.slick.Session
import scala.slick.lifted.TableQuery
import play.api.db.slick.Config.driver.simple._
import mizuchi.models._

class ShowDaoImpl extends ShowDao {
  val table = TableQuery[ShowsTable]
  val inserter = TableQuery[ShowsTable] returning TableQuery[ShowsTable].map(_.id)

  def all(implicit s: Session): Seq[Show] = table.list
  def findById(id: Int)(implicit s: Session): Option[Show] = table.filter(_.id === id).firstOption
  def insert(show: Show)(implicit s: Session): Int = inserter.insert(show)
  def update(show: Show)(implicit s: Session): Unit = table.filter(_.id === show.id).update(show)
}
