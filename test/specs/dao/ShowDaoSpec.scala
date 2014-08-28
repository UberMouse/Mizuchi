package specs.dao

import specs.UnitSpec
import dao.ShowDaoImpl
import models.Show
import org.scalatestplus.play.OneAppPerSuite
import org.scalatest.BeforeAndAfterAll
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import play.api.test._
import play.api.test.Helpers._
import models._
import play.api.Play

class ShowDaoSpec extends UnitSpec with OneAppPerSuite with BeforeAndAfterAll {
  val table = TableQuery[ShowsTable]
  val shows = Seq(
    Show(1, "Akame ga Kill!", "123", "112233"),
    Show(2, "Sword Art Online", "111", "125334")
  )

  def insert(implicit s: Session) = table.insertAll(shows:_*)

  describe("Show DAO") {
    it("Can retrieve all shows") {
      DB.withSession { implicit s =>
        insert
        val dao = new ShowDaoImpl

        dao.all mustEqual shows
      }
    }

    it("Can retrieve a show by Id") {
      DB.withSession { implicit s =>
        val dao = new ShowDaoImpl

        dao.findById(2).get mustEqual shows.last
      }
    }

    it("Can insert a new show") {
      DB.withSession { implicit s =>
        val dao = new ShowDaoImpl
        val show = Show(3, "Zankyou no Terror", "412", "21565")

        dao.insert(show)

        table.list.last mustEqual show
      }
    }

    it("Returns the id of an inserted show") {
      DB.withSession { implicit s =>
        val dao = new ShowDaoImpl
        val show = Show(3, "Zankyou no Terror", "412", "21565")

        val id = dao.insert(show)

        table.list.last.id mustEqual id
      }
    }

    it("Can update a show") {
      DB.withSession { implicit s =>
        val dao = new ShowDaoImpl
        val show = table.list.last.copy(name = "Baccano!")

        dao.update(show)

        table.list.last mustEqual show
      }
    }
  }
}
