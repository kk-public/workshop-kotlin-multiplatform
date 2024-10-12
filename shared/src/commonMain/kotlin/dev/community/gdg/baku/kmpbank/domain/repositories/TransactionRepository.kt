package dev.community.gdg.baku.kmpbank.domain.repositories

import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun syncTransactions(customer: Customer, card: Card)
    fun observeTransactionsByCard(card: Card): Flow<List<Transaction>>
    suspend fun clearAll()
}