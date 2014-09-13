package mizuchi.controllers

import play.api.Play.current
import play.api.mvc._
import scaldi.{ Injectable, Injector }
import mizuchi.services.ShowService

class Application(implicit inj: Injector) extends Controller with Injectable {
  val service = inject[ShowService]

  def index = Action {
    play.api.db.slick.DB.withSession { implicit s =>
      Ok(mizuchi.views.html.main("Index", service.list))
    }
  }

}