import java.net.URI
import ControllerSetup.Controller

object Routes {
  import SimpleController._
  def getRoutes: Map[(HttpMethod, URI), Controller[String]] = {

    Map(
      (POST, new URI("/test")) -> TestController
    )

  }
}
