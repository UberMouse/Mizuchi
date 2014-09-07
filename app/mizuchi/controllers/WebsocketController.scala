package mizuchi.controllers

import scaldi.{Injectable, Injector}
import play.api.mvc._
import play.api.libs.iteratee._

class WebsocketController(implicit inj: Injector) extends Controller with Injectable {
  def connect = WebSocket.using[String] { request =>
  // Concurrent.broadcast returns (Enumerator, Concurrent.Channel)
    val (out, channel) = Concurrent.broadcast[String]

    // log the message to stdout and send response back to client
    val in = Iteratee.foreach[String] {
      msg => println(msg)
        // the Enumerator returned by Concurrent.broadcast subscribes to the channel and will
        // receive the pushed messages
        channel push("I received your message: " + msg)
    }
    (in, out)
  }
}
