package mizuchi.dao

import mizuchi.models.{ SeasonMappingsTable, ShowsTable, SeasonMapping }
import scala.slick.lifted.TableQuery
import play.api.db.slick.Config.driver.simple._

class SeasonMappingDaoImpl extends SeasonMappingDao {
  val table = TableQuery[SeasonMappingsTable]
  val inserter = TableQuery[SeasonMappingsTable] returning TableQuery[SeasonMappingsTable].map(_.id)

  def create(tvdbId: String, season: Int): Int = {
    val mapping = SeasonMapping(-1, tvdbId, season)
    inSession { implicit s =>
      inserter insert mapping
    }
  }
}
