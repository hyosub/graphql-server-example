package io.hyosub.example.graphql.ktor

import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import graphql.GraphQL
import io.hyosub.example.graphql.schema.SchemaFactory
import io.hyosub.example.graphql.schema.dataloader.LinkedAccountsDataLoader
import io.hyosub.example.graphql.schema.dataloader.OwnerDataLoader
import io.hyosub.example.graphql.repository.AccountRepository
import io.hyosub.example.graphql.repository.UserRepository

class KtorCallServerContext: AutoCloseable {

  val mapper by lazy {
    jacksonObjectMapper()
  }

  val graphQLServer: KtorGraphQLServer by lazy {
    KtorGraphQLServer(requestParser, graphQLContextFactory, requestHandler)
  }

  val callServer: KtorCallServer by lazy {
    KtorCallServer(this)
  }

  private val repositoryManager by lazy {
    RepositoryManager(
      UserRepository(),
      AccountRepository()
    )
  }

  private val dataLoaderManager by lazy {
    DataLoaderManager(
      OwnerDataLoader("OwnerDataLoader", repositoryManager),
      LinkedAccountsDataLoader("LinkedAccountsDataLoader", repositoryManager)
    )
  }

  private val graphQLSchema: GraphQL by lazy {
    val graphqlFactory = SchemaFactory(repositoryManager)
    graphqlFactory.createSchema()
  }

  private val dataLoaderRegistryFactory: KtorDataLoaderRegistryFactory by lazy {
    KtorDataLoaderRegistryFactory(dataLoaderManager)
  }

  private val requestParser: KtorGraphQLRequestParser by lazy {
    KtorGraphQLRequestParser(mapper)
  }

  private val requestHandler: GraphQLRequestHandler by lazy {
    GraphQLRequestHandler(graphQLSchema, dataLoaderRegistryFactory)
  }

  private val graphQLContextFactory: KtorGraphQLContextFactory by lazy {
    KtorGraphQLContextFactory()
  }

  fun init(): KtorCallServerContext {
    // 생성 라이프사이클을 위해 순서를 보장할 수 있도록 배치
    mapper
    repositoryManager
    dataLoaderManager
    dataLoaderRegistryFactory
    graphQLSchema
    requestParser
    requestHandler
    graphQLContextFactory
    graphQLServer
    callServer
    return this
  }

  override fun close() {
      TODO("Not yet implemented")
  }

}