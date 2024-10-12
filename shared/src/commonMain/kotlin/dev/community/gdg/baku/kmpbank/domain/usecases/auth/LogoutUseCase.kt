package dev.community.gdg.baku.kmpbank.domain.usecases.auth

import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.CardRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.TransactionRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class LogoutUseCase(
    private val authRepo: AuthRepository,
    private val cardRepo: CardRepository,
    private val transactionRepo: TransactionRepository
) : BaseAsyncUseCase<Unit, Unit>() {

    override suspend fun executeRoutine(params: Unit) {
        authRepo.logout()
        authRepo.clearAll()
        cardRepo.clearAll()
        transactionRepo.clearAll()
    }
}