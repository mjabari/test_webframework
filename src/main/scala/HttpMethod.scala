import scala.language.implicitConversions

sealed trait HttpMethod
case object GET extends HttpMethod
case object POST extends HttpMethod

object HttpMethod {
    implicit def apply(methodInString: String): HttpMethod = methodInString.toLowerCase() match {
        case "get" => GET
        case "post" => POST
    }
}