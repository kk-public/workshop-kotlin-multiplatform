package dev.community.gdg.baku.kmpbank.data.repositories.transaction.remote

import dev.community.gdg.baku.kmpbank.data.repositories.transaction.remote.dto.TransactionRemoteDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

interface TransactionRemoteData {
    suspend fun getTransactionsByCard(
        customerID: String,
        cardID: String
    ): List<TransactionRemoteDTO>
}

class TransactionRemoteDataImpl(
    private val httpClient: HttpClient
) : TransactionRemoteData {
    override suspend fun getTransactionsByCard(
        customerID: String,
        cardID: String
    ): List<TransactionRemoteDTO> {
        return httpClient.get {
            url {
                path("customers/${customerID}/cards/${cardID}/transactions")
            }
        }.body()
    }
}