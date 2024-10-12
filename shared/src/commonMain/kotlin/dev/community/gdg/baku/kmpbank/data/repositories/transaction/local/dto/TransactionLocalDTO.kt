package dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.community.gdg.baku.kmpbank.domain.entities.Currency
import dev.community.gdg.baku.kmpbank.domain.entities.Transaction
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@Entity(
    tableName = "transactions"
)
data class TransactionLocalDTO(
    @PrimaryKey
    val id: String,
    val cardId: String,
    val title: String,
    val amount: String,
    val currency: String,
    val category: String,
    val createdAt: String,
) {
    fun toDomain(): Transaction {
        val formatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        @OptIn(FormatStringsInDatetimeFormats::class)
        val dateTimeFormat = LocalDateTime.Format {
            byUnicodePattern(formatPattern)
        }

        val date = dateTimeFormat.parse(createdAt)

        return Transaction(
            id = id,
            amount = amount.toDoubleOrNull() ?: 0.0,
            currency = when (currency) {
                "AZN" -> Currency.AZN
                "USD" -> Currency.USD
                "EUR" -> Currency.EUR
                else -> Currency.UNKNOWN
            },
            currencyCode = currency,
            category = category,
            description = title,
            date = date
        )
    }
}