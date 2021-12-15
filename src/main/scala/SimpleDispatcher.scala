import com.sun.net.httpserver.{HttpExchange, HttpHandler}

import java.net.URI
import scala.concurrent.ExecutionContext.Implicits.global

class SimpleDispatcher extends HttpHandler{

  override def handle(httpExchange: HttpExchange): Unit = {
    import Routes._
    println(httpExchange.getRequestURI)
    getRoutes.get((httpExchange.getRequestMethod, new URI(httpExchange.getRequestURI.toString.split("\\?")(0)))) match {
      case Some(controller) => {
        val params = httpExchange.getRequestURI.toString.split("\\?")(1).split("&").map
          { v =>
            val kvArray = v.split("=")
            (kvArray(0) -> kvArray(1))
          }.toMap

        controller.handle(SimpleController.request(params), httpExchange).foreach{ response =>
          httpExchange.sendResponseHeaders(200, response.length())
          val body = httpExchange.getResponseBody
          body.write(response.getBytes());
          body.close()
        }
      }
      case _ => {
        httpExchange.sendResponseHeaders(404, 0)
        httpExchange.close()
      }
    }
  }





}
