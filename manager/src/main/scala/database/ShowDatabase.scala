package database

import shows.Show

trait ShowDatabase {
  def all: Iterable[Show]
  def add(show: Show): Unit
  def last: Option[Show]
}
