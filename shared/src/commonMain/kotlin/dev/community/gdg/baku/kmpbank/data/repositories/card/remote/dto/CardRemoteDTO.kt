package dev.community.gdg.baku.kmpbank.data.repositories.card.remote.dto

import dev.community.gdg.baku.kmpbank.data.repositories.card.local.dto.CardLocalDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardRemoteDTO(
    val id: String,
    val currency: String,
    @SerialName("pan")
    val number: String,
    val balance: String,
    val type: String,
    val status: String,
    val createdAt: String,
) {
    fun toLocal() = CardLocalDTO(
        id = id,
        currency = currency,
        number = number,
        balance = balance,
        type = type,
        status = status,
        createdAt = createdAt
    )
}