import com.sun.net.httpserver.HttpServer

import java.net.InetSocketAddress

object Main extends App {
  println("Hello Sanjagh!")
  val httpServer = HttpServer.create(new InetSocketAddress(8090), 0)
  httpServer.createContext("/", new SimpleDispatcher())
  httpServer.start()
}
