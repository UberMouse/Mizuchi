package mizuchi.tests.services

import org.scalatestplus.play.OneAppPerSuite
import scaldi.{Module, Injectable}
import mizuchi.tests.UnitSpec
import mizuchi.services.ShowService
import mizuchi.models.Show
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import mizuchi.Global

class ShowServiceSpec extends UnitSpec with OneAppPerSuite with Injectable {
  val shows = Seq(
    Show(1, "Akame ga Kill!", "123", "112233"),
    Show(2, "Sword Art Online", "111", "125334")
  )
  implicit val module = new Module {

  } :: Global.appModule

  describe("Show Service") {
    it("Can list all shows") {
      DB.withSession { implicit s =>
        val service = inject[ShowService]

        service.list mustEqual shows
      }
    }
  }
}
