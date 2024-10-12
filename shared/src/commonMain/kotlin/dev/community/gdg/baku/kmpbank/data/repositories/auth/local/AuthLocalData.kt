package dev.community.gdg.baku.kmpbank.data.repositories.auth.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.community.gdg.baku.kmpbank.data.repositories.auth.local.dto.CustomerLocalDTO
import dev.community.gdg.baku.kmpbank.data.tools.decodeJson
import dev.community.gdg.baku.kmpbank.data.tools.encodeJson
import dev.community.gdg.baku.kmpbank.domain.exceptions.ClientError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

interface AuthLocalData {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun saveCustomer(value: CustomerLocalDTO)
    suspend fun getCustomer(): CustomerLocalDTO
    fun observeCustomer(): Flow<CustomerLocalDTO?>
    suspend fun clearAll()
}

class AuthLocalDataImpl(
    private val dataStore: DataStore<Preferences>,
) : AuthLocalData {
    private val KEY_TOKEN = "token"
    private val KEY_CUSTOMER = "customer"

    override suspend fun saveToken(token: String) {
        // TODO: implement
    }

    override suspend fun getToken(): String? {
        return dataStore.data.firstOrNull()?.get(stringPreferencesKey(KEY_TOKEN))
    }

    override suspend fun saveCustomer(value: CustomerLocalDTO) {
        dataStore.edit {
            it[stringPreferencesKey(KEY_CUSTOMER)] = value.encodeJson()
        }
    }

    override suspend fun getCustomer(): CustomerLocalDTO {
        return dataStore.data.firstOrNull()?.get(stringPreferencesKey(KEY_CUSTOMER))?.decodeJson()
            ?: throw ClientError.CachedCustomerNotFound
    }

    override fun observeCustomer(): Flow<CustomerLocalDTO?> {
        return dataStore.data.map { prefs ->
            prefs[stringPreferencesKey(KEY_CUSTOMER)]?.decodeJson()
        }
    }

    override suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }
}