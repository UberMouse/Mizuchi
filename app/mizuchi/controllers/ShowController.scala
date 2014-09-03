package mizuchi.controllers

import play.mvc.Controller
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import scaldi.{Injector, Injectable}
import mizuchi.services.ShowService
import play.libs.Json
import argonaut._, Argonaut._
import play.api.mvc.Results.Ok
import play.api.Play.current
import play.api.mvc.Action
import mizuchi.models.Show

class ShowController(implicit inj: Injector) extends Controller with Injectable {
  val service = inject[ShowService]

  def list = Action {
    DB.withSession { implicit s =>
      Ok(service.list.toList.asJson.spaces2)
    }
  }

  def create = Action { request =>
    DB.withSession { implicit s =>
      val show = request.body.toString.decode[Show].getOrElse(null)
      service.create(show)
      Ok("foo")
    }
  }
}
