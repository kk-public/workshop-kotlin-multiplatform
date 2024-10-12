package dev.community.gdg.baku.kmpbank.data.repositories.transaction.local

import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.dto.TransactionLocalDTO
import kotlinx.coroutines.flow.Flow

interface TransactionLocalData {
    fun observeTransactions(): Flow<List<TransactionLocalDTO>>
    suspend fun save(cardID: String, transactions: List<TransactionLocalDTO>)
    suspend fun clearAll()
}

class TransactionLocalDataImpl(
    private val dao: TransactionDAO
) : TransactionLocalData {
    override fun observeTransactions(): Flow<List<TransactionLocalDTO>> {
        // TODO: fix the query
        return dao.observeTransactions()
    }

    override suspend fun save(cardID: String, transactions: List<TransactionLocalDTO>) {
        dao.deleteAllByCard(cardID = cardID)
        dao.save(transactions)
    }

    override suspend fun clearAll() {
        dao.deleteAll()
    }
}