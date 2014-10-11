package mizuchi.dao

import play.api.db.slick.Session
import scala.slick.lifted.TableQuery
import play.api.db.slick.Config.driver.simple._
import mizuchi.models._

class ShowDaoImpl extends ShowDao {
  val table = TableQuery[ShowsTable]
  val inserter = TableQuery[ShowsTable] returning TableQuery[ShowsTable].map(_.id)

  def all: Iterable[Show] = inSession { implicit s =>
    table.list
  }
  def findById(id: Int): Option[Show] = inSession { implicit s =>
    table.filter(_.id === id).firstOption
  }
  def insert(show: Show): Int = inSession { implicit s =>
    inserter.insert(show)
  }
  def update(show: Show): Unit = inSession { implicit s =>
    table.filter(_.id === show.id).update(show)
  }
}
