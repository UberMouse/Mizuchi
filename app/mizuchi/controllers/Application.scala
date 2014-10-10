package mizuchi.controllers

import play.api.Play.current
import play.api.mvc._
import scaldi.{ Injectable, Injector }
import mizuchi.services.ShowService

class Application extends Controller {

  def index(path: String) = Action {
    Ok(mizuchi.views.html.main())
  }

}