package dev.community.gdg.baku.kmpbank.domain.repositories

import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun observeCards(): Flow<List<Card>>
    suspend fun getCards(): List<Card>
    suspend fun syncCards(customer: Customer)
    suspend fun clearAll()
}