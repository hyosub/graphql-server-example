package io.hyosub.example.graphql.ktor.module

import io.hyosub.example.graphql.ktor.KtorCallServer
import io.hyosub.example.graphql.ktor.KtorCallServerContext
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.graphql() {

  val callServer = getCallServer()

  //environment.monitor.subscribe(ApplicationStopped, ::closeContext)

  install(Routing)

  routing {
    post("graphql") {
      callServer.handle(call)
    }
    get("playground") {
      this.call.respondText(buildPlaygroundHtml("graphql", "subscriptions"), ContentType.Text.Html)
    }
  }

}

private fun getCallServer(): KtorCallServer {
  val context = KtorCallServerContext().init()
  return context.callServer
}

private fun closeContext(application: Application) {

}

private fun buildPlaygroundHtml(graphQLEndpoint: String, subscriptionsEndpoint: String) =
  Application::class.java.classLoader.getResource("graphql-playground.html")?.readText()
    ?.replace("\${graphQLEndpoint}", graphQLEndpoint)
    ?.replace("\${subscriptionsEndpoint}", subscriptionsEndpoint)
    ?: throw IllegalStateException("graphql-playground.html cannot be found in the classpath")
