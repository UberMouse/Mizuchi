package mizuchi.models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.Json

case class SeasonMapping(id: Int, tvdb_id: String, seasonOverride: Int)

object SeasonMapping {
  implicit def ShowFormat = Json.format[Show]
}

class SeasonMappingsTable(tag: Tag) extends Table[SeasonMapping](tag, "SEASON_MAPPINGS") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def seasonOverride = column[Int]("seasonOverride", O.NotNull)
  def tvdb_id = column[String]("tvdb_id", O.NotNull)

  def * = (id, tvdb_id, seasonOverride) <> ((SeasonMapping.apply _).tupled, SeasonMapping.unapply _)
}
