package dev.community.gdg.baku.kmpbank.data.repositories.transaction.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.dto.TransactionLocalDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDAO {
    @Query("SELECT * FROM transactions WHERE cardId = :cardID")
    suspend fun getTransactions(cardID: String): List<TransactionLocalDTO>

    @Query("SELECT * FROM transactions WHERE cardId = :cardID")
    fun observeTransactions(cardID: String): Flow<List<TransactionLocalDTO>>

    @Upsert
    suspend fun save(transactions: List<TransactionLocalDTO>)

    @Delete
    suspend fun delete(transaction: TransactionLocalDTO)

    @Query("DELETE FROM transactions WHERE cardId = :cardID")
    suspend fun deleteAllByCard(cardID: String)

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()
}