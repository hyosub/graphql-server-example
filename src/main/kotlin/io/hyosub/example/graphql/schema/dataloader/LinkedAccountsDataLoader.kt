package io.hyosub.example.graphql.schema.dataloader

import com.expediagroup.graphql.server.execution.KotlinDataLoader
import io.hyosub.example.graphql.ktor.RepositoryManager
import io.hyosub.example.graphql.schema.model.Account
import kotlinx.coroutines.runBlocking
import org.dataloader.DataLoader
import java.util.*
import java.util.concurrent.CompletableFuture

class LinkedAccountsDataLoader(
  override val dataLoaderName: String,
  manager: RepositoryManager
) : KotlinDataLoader<List<String>, List<Account>> {

  private val accountRepository = manager.accountRepository

  override fun getDataLoader() = DataLoader<List<String>, List<Account>> { numbers ->
    CompletableFuture.supplyAsync {
      val accounts = runBlocking {
        accountRepository.findAll(numbers.flatten()).toMutableList()
      }
      val list = LinkedList<List<Account>>()
      list.add(accounts)
      list
    }
  }

}