package specs.services

import specs.UnitSpec
import org.scalatestplus.play.OneAppPerSuite
import scaldi.{Module, Injectable}

class ShowServiceSpec extends UnitSpec with OneAppPerSuite with Injectable {
  val module = new Module {

  }
  describe("Show Service") {
    it("Can list all shows") {

    }
  }
}
