package mizuchi.dao

import play.api.db.slick.DB
import play.api.Play.current
import scala.slick.jdbc.JdbcBackend

trait Dao {
  def inSession[T](transaction: (JdbcBackend.Session) => T) = DB.withSession { implicit s =>
    transaction(s)
  }
}
