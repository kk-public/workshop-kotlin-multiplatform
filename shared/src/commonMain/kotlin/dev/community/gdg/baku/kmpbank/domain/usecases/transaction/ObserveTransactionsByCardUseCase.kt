package dev.community.gdg.baku.kmpbank.domain.usecases.transaction

import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import dev.community.gdg.baku.kmpbank.domain.repositories.TransactionRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseObservableUseCase
import kotlinx.coroutines.flow.Flow

class ObserveTransactionsByCardUseCase(
    private val repo: TransactionRepository
) : BaseObservableUseCase<Card, List<Transaction>>() {

    override fun observeOn(params: Card): Flow<List<Transaction>> {
        return repo.observeTransactionsByCard(params)
    }
}