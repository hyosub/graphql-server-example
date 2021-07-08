package io.hyosub.example.graphql.schema

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import graphql.GraphQL
import io.hyosub.example.graphql.ktor.RepositoryManager
import io.hyosub.example.graphql.schema.query.AccountQueryService
import io.hyosub.example.graphql.schema.query.UserQueryService
import java.util.*

class SchemaFactory(manager: RepositoryManager) {

  private val rootPackage = "io.hyosub.example.graphql"

  private val config = SchemaGeneratorConfig(supportedPackages = listOf(rootPackage))
  private val queries = listOf(
    TopLevelObject(UserQueryService(manager)),
    TopLevelObject(AccountQueryService(manager))
  )
  private val mutations = LinkedList<TopLevelObject>()
  private val schema = toSchema(config, queries, mutations)

  fun createSchema(): GraphQL {
    return GraphQL.newGraphQL(schema).build()
  }

}