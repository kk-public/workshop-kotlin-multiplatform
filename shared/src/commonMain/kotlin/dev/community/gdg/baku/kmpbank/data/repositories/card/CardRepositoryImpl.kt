package dev.community.gdg.baku.kmpbank.data.repositories.card

import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardLocalData
import dev.community.gdg.baku.kmpbank.data.repositories.card.remote.CardRemoteData
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CardRepositoryImpl(
    private val localData: CardLocalData,
    private val remoteData: CardRemoteData
) : CardRepository {
    override fun observeCards(): Flow<List<Card>> {
        return localData.observeCards().map { it.map { card -> card.toDomain() } }
    }

    override suspend fun getCards(): List<Card> {
        return localData.getCards().map { it.toDomain() }
    }

    override suspend fun syncCards(customer: Customer) {
        val card = remoteData.getCards(customer.id)
        localData.saveCards(card.map { it.toLocal() })
    }

    override suspend fun clearAll() {
        localData.clearAll()
    }
}