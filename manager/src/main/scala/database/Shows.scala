package database

import scala.slick.driver.SQLiteDriver.simple._
import shows.Show

class Shows(tag: Tag) extends Table[shows.Show](tag, "SHOWS") {
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def * = (name, id) <> (Show.tupled, Show.unapply)
}
