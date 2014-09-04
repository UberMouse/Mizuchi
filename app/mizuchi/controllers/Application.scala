package mizuchi.controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
   Ok(mizuchi.views.html.main("Index"))
  }

}