package mizuchi.dao

trait SeasonMappingDao extends Dao {
  def create(tvdbId: String, season: Int): Int
}
