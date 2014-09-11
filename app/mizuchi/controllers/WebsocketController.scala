package mizuchi.controllers

import scaldi.{Injectable, Injector}
import play.api.mvc._
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import mizuchi.models.{Show, ActionResult, Action}
import mizuchi.dao.ShowDao
import play.api.Logger
import play.api.Play.current
import org.json4s._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}
import native.JsonMethods

class WebsocketController(implicit inj: Injector) extends Controller with Injectable {



  def connect = WebSocket.using[String] { request =>
    // Concurrent.broadcast returns (Enumerator, Concurrent.Channel)
    val (out, channel) = Concurrent.broadcast[String]

    // log the message to stdout and send response back to client
    val in = Iteratee.foreach[String] { msg =>

    }
    (in, out)
  }

//  private def handleAction(action: Action): ActionResult = action.action_name match {
//    case "CREATE_SHOW" => {
//      logger.info("Handling CREATE_SHOW action")
//      val name = action.args("name")
//      val show = Show(-1, name, "1", "1")
//      play.api.db.slick.DB.withSession {
//        implicit s =>
//          showDao.insert(show)
//      }
//      logger.info(s"Inserted show: $show")
//      ActionResult(success = true)
//    }
//    case _ => {
//      logger.info(s"Action (${action.action_name}) not handled")
//      ActionResult(success = false, message = Option(s"${action.action_name} is not a valid action"))
//    }
//  }
}
