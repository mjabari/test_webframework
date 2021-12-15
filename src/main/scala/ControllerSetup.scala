import SimpleController.request
import com.sun.net.httpserver.HttpExchange

import scala.concurrent.Future
import scala.language.implicitConversions
object ControllerSetup {
    trait Controller[B] {
        def handle(request: request, httpExchange: HttpExchange): Future[B]
    }

    implicit final class Unmarshal[A](private val exchange: HttpExchange) {
        def proc(implicit unmarshaller: Unmarshaller[A]): A = unmarshaller(exchange)
    }

    trait Unmarshaller[A] {
        def apply(exchange: HttpExchange): A
    }
}