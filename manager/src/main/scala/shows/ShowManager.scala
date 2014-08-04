package shows

import database.ShowDatabase

case class Show(id: Int, name: String)

class ShowManager(db: ShowDatabase) {
  def all = List(Show(10, "a")) ++ db.all
  def add(name: String) = {
    val lastId = db.last.map(_.id).getOrElse(0)
    db.add(Show(lastId + 1, name))
  }
}
