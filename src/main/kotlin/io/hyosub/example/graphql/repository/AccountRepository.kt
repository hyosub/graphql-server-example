package io.hyosub.example.graphql.repository

import io.hyosub.example.graphql.schema.model.Account
import io.hyosub.example.graphql.schema.model.User

class AccountRepository {

  private val data = listOf(
    Account("1111", 10000, "20210101", "1"),
    Account("1112", 1000, "20210101", "1"),
    Account("1113", 20000, "20210101", "2"),
    Account("1114", 500, "20210101", "2")
  )

  fun findByAccountNumber(accountNumber: String): Account? {
    return data.filter { it.number == accountNumber }.takeIf { it.isNotEmpty() }?.get(0)
  }

  fun findByUserId(userId: String): List<Account> {
    return data.filter { it.ownerId == userId }
  }

  fun findAll(accountNumbers: List<String>): List<Account> {
    return data.filter { accountNumbers.contains(it.number) }
  }

}