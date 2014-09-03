package mizuchi.models

import play.api.db.slick.Config.driver.simple._
import argonaut.Argonaut._

case class Show(id: Int, name: String, hummingbird_id: String, tvdb_id: String)

object Show {
  implicit def ShowCodec = casecodec4(Show.apply, Show.unapply)("id", "name", "hummingbird_id", "tvdb_id")
}

class ShowsTable(tag: Tag) extends Table[Show](tag, "SHOWS") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.NotNull)
  def hummingbird_id = column[String]("hummingbird_id", O.NotNull)
  def tvdb_id = column[String]("tvdb_id", O.NotNull)

  def * = (id, name, hummingbird_id, tvdb_id) <> ((Show.apply _).tupled, Show.unapply _)
}
