package io.hyosub.example.graphql.ktor

import io.hyosub.example.graphql.schema.dataloader.LinkedAccountsDataLoader
import io.hyosub.example.graphql.schema.dataloader.OwnerDataLoader

data class DataLoaderManager(
  val ownerDataLoader: OwnerDataLoader,
  val linkedAccountsDataLoader: LinkedAccountsDataLoader
)