package io.hyosub.example.graphql.schema.query

import com.expediagroup.graphql.server.operations.Query
import io.hyosub.example.graphql.ktor.RepositoryManager
import io.hyosub.example.graphql.schema.model.Account

class AccountQueryService(manager: RepositoryManager) : Query {

  private val accountRepository = manager.accountRepository

  fun accounts(userId: String): List<Account> {
    return accountRepository.findByUserId(userId)
  }

}
