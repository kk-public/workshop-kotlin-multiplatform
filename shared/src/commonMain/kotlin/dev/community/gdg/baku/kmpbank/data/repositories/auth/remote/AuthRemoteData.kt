package dev.community.gdg.baku.kmpbank.data.repositories.auth.remote

import dev.community.gdg.baku.kmpbank.data.repositories.auth.remote.dto.CustomerRemoteDTO
import dev.community.gdg.baku.kmpbank.domain.exceptions.ClientError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import kotlinx.coroutines.delay

interface AuthRemoteData {
    suspend fun authorize(email: String, password: String): String
    suspend fun getCustomer(id: String): CustomerRemoteDTO
    suspend fun logout()
}

class AuthRemoteDataImpl(
    private val httpClient: HttpClient
) : AuthRemoteData {
    override suspend fun authorize(email: String, password: String): String {
        // fake network call
        delay(1000)
        if (email == "example@company.com" && password == "password") {
            return "token"
        }

        throw ClientError.InvalidUserCredentials
    }

    override suspend fun getCustomer(id: String): CustomerRemoteDTO {
        return httpClient.get {
            url {
                path("customers/${id}")
            }
        }.body()
    }

    override suspend fun logout() {
        // fake network call
        delay(1000)
    }
}