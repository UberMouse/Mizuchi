package database

import shows.Show

trait ShowDatabase {
  def all: List[Show]
}
