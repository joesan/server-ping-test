package com.egc.healthtest

import org.jfarcand.wcs.{TextListener, WebSocket}
import java.net.InetAddress

/**
 * Check if the server is running healthy making a test call against it.
 * This assumes that the server is hosting a WebSocket Endpoint.
 * 
 * If the server responds with a proper message, the program terminates
 * but if not, you can attach the specific behavior by modifying the
 * batch file healthtest.bat
 */
object ServerHealthTest {

  def main(args: Array[String]): Unit = {
    try {
      val host = if(args(0).isEmpty) "localhost" else args(0)
      val port = if(args(1).isEmpty) "9000" else args(1)
	  val service = args(2)

      val url: String = s"ws://$host:$port/$service"

      val ws = WebSocket().open(url)
      ws.listener(new TextListener {
        override def onMessage(message: String) {
          println(message)
          System.exit(0)
        }

        override def onError(t : scala.Throwable) {
          t.printStackTrace()
          System.exit(-1)
        }
      })
      sendMessageToSocket(ws)
      return
    } catch {
      case t: Throwable => {
        t.printStackTrace()
        System.exit(-1)
      }
    }
  }

  // Fill in the data that needs to be sent to the server
  val data =
    """
    """.stripMargin

  private def sendMessageToSocket(ws: WebSocket) = ws.send(data)
}