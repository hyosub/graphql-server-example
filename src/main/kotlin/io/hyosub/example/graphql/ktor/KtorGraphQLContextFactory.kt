package io.hyosub.example.graphql.ktor

import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import io.hyosub.example.graphql.schema.model.User
import io.ktor.request.*

class KtorGraphQLContextFactory : GraphQLContextFactory<AuthorizedContext, ApplicationRequest> {

  override suspend fun generateContext(request: ApplicationRequest): AuthorizedContext {
    val loggedInUser = User("fakeUser", "fake Kim", "20210101")
    val customHeader: String? = request.headers["my-custom-header"]
    return AuthorizedContext(loggedInUser, customHeader)
  }

}