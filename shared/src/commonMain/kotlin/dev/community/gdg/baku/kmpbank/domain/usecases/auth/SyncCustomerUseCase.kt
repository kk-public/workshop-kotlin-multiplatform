package dev.community.gdg.baku.kmpbank.domain.usecases.auth

import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class SyncCustomerUseCase(
    private val repo: AuthRepository,
) : BaseAsyncUseCase<Unit, Unit>() {

    override suspend fun executeRoutine(params: Unit) {
        repo.syncCustomer()
    }
}