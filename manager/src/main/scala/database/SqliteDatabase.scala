package database

import shows.Show
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.jdbc.meta.MTable

class SqliteDatabase extends ShowDatabase {
  Class.forName("org.sqlite.JDBC")
  val db = Database.forURL("jdbc:sqlite:manager.db")
  val shows = TableQuery[Shows]
  private implicit val session = db.createSession()

  db withSession {implicit session =>
    if(MTable.getTables (shows.baseTableRow.tableName) .list.isEmpty)
      shows.ddl.create
  }

  def all: Iterable[Show] = shows.list
  def add(show: Show): Unit = shows.insert(show)
  def last: Option[Show] = shows.sortBy(_.id).take(1).firstOption

}
