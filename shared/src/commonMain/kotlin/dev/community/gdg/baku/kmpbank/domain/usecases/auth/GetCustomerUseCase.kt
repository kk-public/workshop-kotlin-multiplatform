package dev.community.gdg.baku.kmpbank.domain.usecases.auth

import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class GetCustomerUseCase(
    private val repo: AuthRepository,
) : BaseAsyncUseCase<Unit, Customer>() {

    override suspend fun executeRoutine(params: Unit): Customer {
        return repo.getCustomer()
    }
}