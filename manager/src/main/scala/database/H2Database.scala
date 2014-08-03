package database

import shows.Show
import scala.slick.driver.H2Driver.simple._

class H2Database extends ShowDatabase {
  def all: List[Show] = null
}
