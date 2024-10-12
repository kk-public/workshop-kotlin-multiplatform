package dev.community.gdg.baku.kmpbank.domain.usecases.card

import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.repositories.CardRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class GetCardsUseCase(
    private val repo: CardRepository
) : BaseAsyncUseCase<Unit, List<Card>>() {

    override suspend fun executeRoutine(params: Unit): List<Card> {
        return repo.getCards()
    }
}