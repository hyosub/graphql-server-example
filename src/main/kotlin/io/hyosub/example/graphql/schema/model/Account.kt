package io.hyosub.example.graphql.schema.model

import com.expediagroup.graphql.server.extensions.getValueFromDataLoader
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

data class Account(
  val number: String,
  val balance: Int,
  val createdAt: String,
  val ownerId: String
) {

  fun owner(environment: DataFetchingEnvironment): CompletableFuture<User> {
    return environment.getValueFromDataLoader("OwnerDataLoader", ownerId)
  }

}
