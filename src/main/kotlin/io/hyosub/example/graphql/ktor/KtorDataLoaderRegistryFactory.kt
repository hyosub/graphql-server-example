package io.hyosub.example.graphql.ktor

import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import org.dataloader.DataLoaderRegistry

class KtorDataLoaderRegistryFactory(
  private val manager: DataLoaderManager
) : DataLoaderRegistryFactory {

  override fun generate(): DataLoaderRegistry {
    val ownerDataLoader = manager.ownerDataLoader
    val linkedAccountsDataLoader = manager.linkedAccountsDataLoader

    val registry = DataLoaderRegistry()
    registry.register(ownerDataLoader.dataLoaderName, ownerDataLoader.getDataLoader())
    registry.register(linkedAccountsDataLoader.dataLoaderName, linkedAccountsDataLoader.getDataLoader())
    return registry
  }

}