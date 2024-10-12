package dev.community.gdg.baku.kmpbank.data.repositories.transaction.remote.dto

import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.dto.TransactionLocalDTO
import kotlinx.serialization.Serializable

@Serializable
data class TransactionRemoteDTO(
    val id: String,
    val cardId: String,
    val title: String,
    val amount: String,
    val currency: String,
    val category: String,
    val createdAt: String,
) {
    fun toLocal(): TransactionLocalDTO {
        return TransactionLocalDTO(
            id = id,
            cardId = cardId,
            title = title,
            amount = amount,
            currency = currency,
            category = category,
            createdAt = createdAt
        )
    }
}
