package io.hyosub.example.graphql.repository

import io.hyosub.example.graphql.schema.model.User

class UserRepository {

  private val data = listOf(
    User("1", "김길동", "20210101", listOf("1111", "1112")),
    User("2", "이길동", "20210102", listOf("1113", "1114"))
  )

  fun findById(id: String): User? {
    return data.filter { it.id == id }.takeIf { it.isNotEmpty() }?.get(0)
  }

  fun findAll(ids: List<String>): List<User> {
    return data.filter { ids.contains(it.id) }
  }

}