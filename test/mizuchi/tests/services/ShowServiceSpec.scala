package mizuchi.tests.services

import org.scalatestplus.play.OneAppPerSuite
import scaldi.{Module, Injectable}
import mizuchi.tests.UnitSpec

class ShowServiceSpec extends UnitSpec with OneAppPerSuite with Injectable {
  val module = new Module {

  }
  describe("Show Service") {
    it("Can list all shows") {

    }
  }
}
