package dev.community.gdg.baku.kmpbank.domain.usecases.transaction

import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.TransactionRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class SyncTransactionsByCardUseCase(
    private val authRepo: AuthRepository,
    private val transactionRepo: TransactionRepository
) : BaseAsyncUseCase<Card, Unit>() {

    override suspend fun executeRoutine(params: Card) {
        val customer = authRepo.getCustomer()
        transactionRepo.syncTransactions(customer, params)
    }
}