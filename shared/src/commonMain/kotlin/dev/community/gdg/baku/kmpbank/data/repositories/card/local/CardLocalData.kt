package dev.community.gdg.baku.kmpbank.data.repositories.card.local

import dev.community.gdg.baku.kmpbank.data.repositories.card.local.dto.CardLocalDTO
import kotlinx.coroutines.flow.Flow

interface CardLocalData {
    fun observeCards(): Flow<List<CardLocalDTO>>
    suspend fun getCards(): List<CardLocalDTO>
    suspend fun saveCards(cards: List<CardLocalDTO>)
    suspend fun clearAll()
}

class CardLocalDataImpl(
    private val cardDAO: CardDAO
) : CardLocalData {

    override fun observeCards(): Flow<List<CardLocalDTO>> = cardDAO.observeCards()

    override suspend fun getCards(): List<CardLocalDTO> {
        return cardDAO.getCards()
    }

    override suspend fun saveCards(cards: List<CardLocalDTO>) {
        cardDAO.deleteAll()
        cardDAO.save(cards)
    }

    override suspend fun clearAll() {
        cardDAO.deleteAll()
    }
}