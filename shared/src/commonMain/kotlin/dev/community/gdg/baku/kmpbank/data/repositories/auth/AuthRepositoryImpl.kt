package dev.community.gdg.baku.kmpbank.data.repositories.auth

import dev.community.gdg.baku.kmpbank.data.repositories.auth.local.AuthLocalData
import dev.community.gdg.baku.kmpbank.data.repositories.auth.remote.AuthRemoteData
import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val authLocalData: AuthLocalData,
    private val authRemoteData: AuthRemoteData
) : AuthRepository {
    override suspend fun authorize(email: String, password: String) {
        val token = authRemoteData.authorize(email, password)
        authLocalData.saveToken(token)
    }

    override suspend fun isAuthorized(): Boolean {
        return authLocalData.getToken() != null
    }

    override suspend fun syncCustomer() {
        val customer = authRemoteData.getCustomer(id = "1") // hardcoded customer id
        authLocalData.saveCustomer(customer.toLocal())
    }

    override suspend fun getCustomer(): Customer {
        return authLocalData.getCustomer().toDomain()
    }

    override fun observeCustomer(): Flow<Customer?> {
        return authLocalData.observeCustomer().map { it?.toDomain() }
    }

    override suspend fun logout() {
        authRemoteData.logout()
    }

    override suspend fun clearAll() {
        authLocalData.clearAll()
    }
}