package shows

import database.ShowDatabase

case class Show(name: String, id: Int = -1)

class ShowManager(db: ShowDatabase) {
  def all = db.all
  def add(show: Show) = {
    val lastId = db.last.map(_.id).getOrElse(0)
    //This is going to bite me later. Calling it now.
    db.add(show.copy(id = lastId + 1))
  }
}
