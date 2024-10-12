package dev.community.gdg.baku.kmpbank.data.repositories.card.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.community.gdg.baku.kmpbank.domain.entities.Card
import dev.community.gdg.baku.kmpbank.domain.entities.Currency

@Entity(tableName = "cards")
data class CardLocalDTO(
    @PrimaryKey
    val id: String,
    val currency: String,
    val number: String,
    val balance: String,
    val type: String,
    val status: String,
    val createdAt: String,
) {
    fun toDomain() = Card(
        id = id,
        number = number,
        currency = when (currency) {
            "AZN" -> Currency.AZN
            "USD" -> Currency.USD
            "EUR" -> Currency.EUR
            else -> Currency.UNKNOWN
        },
        currencyCode = currency,
        balance = balance.toDoubleOrNull() ?: 0.0,
        network = when (type) {
            "visa" -> Card.Network.VISA
            "master" -> Card.Network.MASTERCARD
            "union_pay" -> Card.Network.UNIONPAY
            else -> Card.Network.UNKNOWN
        },
        status = when (status) {
            "active" -> Card.Status.ACTIVE
            "blocked" -> Card.Status.BLOCKED
            "expired" -> Card.Status.EXPIRED
            else -> Card.Status.UNKNOWN
        }
    )
}