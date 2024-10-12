package dev.community.gdg.baku.kmpbank.data.repositories.card.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.dto.CardLocalDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {

    @Query("SELECT * FROM cards")
    suspend fun getCards(): List<CardLocalDTO>

    @Query("SELECT * FROM cards")
    fun observeCards(): Flow<List<CardLocalDTO>>

    @Upsert
    suspend fun save(cards: List<CardLocalDTO>)

    @Delete
    suspend fun delete(card: CardLocalDTO)

    @Query("DELETE FROM cards")
    suspend fun deleteAll()
}