package dev.community.gdg.baku.kmpbank.domain.usecases.card

import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.CardRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class SyncCardsUseCase(
    private val authRepo: AuthRepository,
    private val cardRepo: CardRepository
) : BaseAsyncUseCase<Unit, Unit>() {

    override suspend fun executeRoutine(params: Unit) {
        val customer = authRepo.getCustomer()
        cardRepo.syncCards(customer)
    }
}