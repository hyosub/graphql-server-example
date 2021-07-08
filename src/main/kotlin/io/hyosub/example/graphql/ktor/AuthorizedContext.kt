package io.hyosub.example.graphql.ktor

import com.expediagroup.graphql.generator.execution.GraphQLContext
import io.hyosub.example.graphql.schema.model.User

data class AuthorizedContext(
  val authorizedUser: User? = null,
  var guestUUID: String? = null,
  val customHeader: String? = null
) : GraphQLContext