package dev.community.gdg.baku.kmpbank.data.repositories.transaction

import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.TransactionLocalData
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.remote.TransactionRemoteData
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import dev.community.gdg.baku.kmpbank.domain.repositories.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val localData: TransactionLocalData,
    private val repository: TransactionRemoteData
) : TransactionRepository {
    override suspend fun syncTransactions(customer: Customer, card: Card) {
        val transactions = repository.getTransactionsByCard(customer.id, card.id)
        localData.save(cardID = card.id, transactions = transactions.map { it.toLocal() })
    }

    override fun observeTransactionsByCard(card: Card): Flow<List<Transaction>> {
        // TODO: return transactions by card
        return localData.observeTransactions()
            .map { it.map { transaction -> transaction.toDomain() } }
    }

    override suspend fun clearAll() {
        localData.clearAll()
    }
}