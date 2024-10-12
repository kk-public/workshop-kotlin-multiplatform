package dev.community.gdg.baku.kmpbank.domain.usecases.auth

import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseObservableUseCase
import kotlinx.coroutines.flow.Flow

class ObserveCustomerUseCase(
    private val repo: AuthRepository
) : BaseObservableUseCase<Unit, Customer?>() {

    override fun observeOn(params: Unit): Flow<Customer?> {
        return repo.observeCustomer()
    }
}