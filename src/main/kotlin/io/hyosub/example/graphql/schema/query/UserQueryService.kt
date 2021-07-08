package io.hyosub.example.graphql.schema.query

import com.expediagroup.graphql.server.operations.Query
import io.hyosub.example.graphql.ktor.RepositoryManager
import io.hyosub.example.graphql.schema.model.User

class UserQueryService(manager: RepositoryManager) : Query {

  private val userRepository = manager.userRepository

  fun user(id: String): User? {
    return userRepository.findById(id)
  }

}
