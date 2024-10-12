package dev.community.gdg.baku.kmpbank.domain.usecases.card

import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.repositories.CardRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseObservableUseCase
import kotlinx.coroutines.flow.Flow

class ObserveCardsUseCase(
    private val repo: CardRepository
) : BaseObservableUseCase<Unit, List<Card>>() {

    override fun observeOn(params: Unit): Flow<List<Card>> {
        return repo.observeCards()
    }
}