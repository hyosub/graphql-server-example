package io.hyosub.example.graphql.schema.model

import com.expediagroup.graphql.server.extensions.getValueFromDataLoader
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

data class User(
  val id: String,
  val name: String,
  val createdAt: String,
  val accountNumbers: List<String> = listOf()
) {

  fun linkedAccounts(environment: DataFetchingEnvironment): CompletableFuture<List<Account>> {
    return environment.getValueFromDataLoader("LinkedAccountsDataLoader", accountNumbers)
  }

}
