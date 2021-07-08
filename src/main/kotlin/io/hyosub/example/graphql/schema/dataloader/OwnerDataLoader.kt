package io.hyosub.example.graphql.schema.dataloader

import com.expediagroup.graphql.server.execution.KotlinDataLoader
import io.hyosub.example.graphql.ktor.RepositoryManager
import io.hyosub.example.graphql.schema.model.User
import kotlinx.coroutines.runBlocking
import org.dataloader.DataLoader
import java.util.concurrent.CompletableFuture

class OwnerDataLoader(
  override val dataLoaderName: String,
  manager: RepositoryManager
) : KotlinDataLoader<String, User?> {

  private val userRepository = manager.userRepository

  override fun getDataLoader() = DataLoader<String, User?> { ids ->
    CompletableFuture.supplyAsync {
      runBlocking {
        ids.map { id ->
          userRepository.findById(id)
        }
      }
    }
  }

}