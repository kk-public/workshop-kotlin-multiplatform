package dev.community.gdg.baku.kmpbank.data.repositories.card.remote

import dev.community.gdg.baku.kmpbank.data.repositories.card.remote.dto.CardRemoteDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

interface CardRemoteData {
    suspend fun getCards(customerID: String): List<CardRemoteDTO>
}

class CardRemoteDataImpl(
    private val httpClient: HttpClient
) : CardRemoteData {
    override suspend fun getCards(customerID: String): List<CardRemoteDTO> {
        return httpClient.get {
            url {
                path("customers/${customerID}/cards")
            }
        }.body()
    }
}