package dev.community.gdg.baku.kmpbank.domain.entities

data class Card(
    val id: String,
    val number: String,
    val balance: Double,
    val currency: Currency,
    val currencyCode: String,
    val network: Network,
    val status: Status,
) {
    enum class Network {
        VISA,
        MASTERCARD,
        UNIONPAY,
        UNKNOWN
    }

    enum class Status {
        ACTIVE,
        BLOCKED,
        EXPIRED,
        UNKNOWN;
    }
}