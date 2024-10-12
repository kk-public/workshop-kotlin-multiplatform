package dev.community.gdg.baku.kmpbank.domain.repositories

import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authorize(email: String, password: String)
    suspend fun isAuthorized(): Boolean
    suspend fun syncCustomer()
    suspend fun getCustomer(): Customer
    fun observeCustomer(): Flow<Customer?>
    suspend fun logout()
    suspend fun clearAll()
}