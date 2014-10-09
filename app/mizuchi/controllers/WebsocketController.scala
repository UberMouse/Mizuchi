package mizuchi.controllers

import scaldi.{ Injectable, Injector }
import play.api.mvc._
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import mizuchi.models.{ Show, ActionResult, Action }
import muster.codec.play._
import muster.codec.play.api._

import play.api.libs.json.Json
import mizuchi.sync.ActionDispatcher
import play.api.Logger

class WebsocketController(implicit inj: Injector) extends Controller with Injectable {
  val dispatcher = inject[ActionDispatcher]
  val logger = Logger.logger

  def connect = WebSocket.using[String] { request =>
    // Concurrent.broadcast returns (Enumerator, Concurrent.Channel)
    val (out, channel) = Concurrent.broadcast[String]

    // log the message to stdout and send response back to client
    val in = Iteratee.foreach[String] { actionJson =>
      logger.info(s"Received action: $actionJson")
      val parse = Json.parse(actionJson)
      var action: Action = null
      //doesn't output exceptions from .as call unless this filth is here
      try {
        action = PlayJsonCodec.as[Action](parse)
      } catch {
        case e: Throwable => {
          logger.info("Error parsing action")
          e.printStackTrace()
        }
      }
      if (action != null) {
        logger.info(s"Parsed into: $action")
        val result = dispatcher(action)
        if (result.isFailure)
          result.failed.get.printStackTrace()
        result.map(f => f.onComplete(r => r.foreach(ar => {
          channel push ar.asJsValue.toString()
          logger.info(s"Sent ActionResult for message id ${ar.id} to client")
          logger.info(ar.toString)
        })))
      }
    }
    (in, out)
  }
}
