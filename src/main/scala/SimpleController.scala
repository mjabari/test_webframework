
import com.sun.net.httpserver.HttpExchange
import scala.concurrent.Future

object SimpleController {
  import ControllerSetup._
  case class request(params: Map[String, _] )
  case class MyRequestBody(name: String, age: Int)
  case class MyResponseBody(greet: String)

  object TestController extends Controller[String] {
    implicit val unMarshaller: Unmarshaller[MyRequestBody] = (exchange: HttpExchange) => {
      val in = scala.io.Source.fromInputStream(exchange.getRequestBody).mkString.split(",")
      MyRequestBody(in(0), in(1).toInt)
    }

    override def handle(request: request, exchange: HttpExchange): Future[String] = {
      lazy val body = exchange.proc
      val response = s"Hello ${body.name}, You are ${body.age} years old!"
      Future.successful(response)
    }
  }
}
