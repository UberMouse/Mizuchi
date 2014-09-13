package mizuchi.tests.services

import org.scalatestplus.play.OneAppPerSuite
import scaldi.{ Module, Injectable }
import mizuchi.tests.UnitSpec
import mizuchi.services.ShowService
import mizuchi.models.Show
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import mizuchi.Global
import mizuchi.dao.ShowDao
import play.api.db.slick.Session
import scala.collection.mutable._

class ShowServiceSpec extends UnitSpec with OneAppPerSuite with Injectable {

  class DummyDao(var shows: ListBuffer[Show]) extends ShowDao {
    def all(implicit s: Session): Seq[Show] = shows
    def findById(id: Int)(implicit s: Session): Option[Show] = shows.filter(_.id == id).headOption
    def insert(show: Show)(implicit s: Session): Int = {
      shows += show
      show.id
    }
    def update(show: Show)(implicit s: Session): Unit = ???
  }

  val shows = {
    var buffer = new ListBuffer[Show]()
    buffer += Show(1, "Akame ga Kill!", "123", "112233")
    buffer += Show(2, "Sword Art Online", "111", "125334")
    buffer
  }

  val dao = new DummyDao(shows)
  implicit val module = new Module {
    bind[ShowDao] to dao
  } :: Global.applicationModule

  describe("Show Service") {
    it("Can list all shows") {
      DB.withSession { implicit s =>
        val service = inject[ShowService]

        service.list mustEqual shows
      }
    }

    it("Can create a new show") {
      DB.withSession { implicit s =>
        val service = inject[ShowService]
        val show = Show(10, "Gekkan Shoujo Nozaki-kun", "3173", "19471")

        service.create(show)

        dao.shows.last mustEqual show
      }
    }
  }
}
