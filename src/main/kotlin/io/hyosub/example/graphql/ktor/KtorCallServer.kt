package io.hyosub.example.graphql.ktor

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

class KtorCallServer(context: KtorCallServerContext) {

  private val mapper = context.mapper
  private val graphQLServer = context.graphQLServer

  suspend fun handle(appCall: ApplicationCall) {
    val result = graphQLServer.execute(appCall.request)
    val responseCall = appCall.response.call

    if (result != null) {
      responseCall.respond(mapper.writeValueAsString(result))
    } else {
      responseCall.respond(HttpStatusCode.BadRequest, "Invalid Request")
    }
  }

}