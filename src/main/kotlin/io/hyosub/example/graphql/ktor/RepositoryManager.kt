package io.hyosub.example.graphql.ktor

import io.hyosub.example.graphql.repository.AccountRepository
import io.hyosub.example.graphql.repository.UserRepository

data class RepositoryManager(
  val userRepository: UserRepository,
  val accountRepository: AccountRepository
)