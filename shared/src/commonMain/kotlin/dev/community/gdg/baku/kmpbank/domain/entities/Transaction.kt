package dev.community.gdg.baku.kmpbank.domain.entities

import kotlinx.datetime.LocalDateTime

data class Transaction(
    val id: String,
    val amount: Double,
    val currency: Currency,
    val currencyCode: String,
    val date: LocalDateTime,
    val description: String,
    val category: String
)