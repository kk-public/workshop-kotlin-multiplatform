package dev.community.gdg.baku.kmpbank.data.repositories.auth.local.dto

import dev.community.gdg.baku.kmpbank.domain.entities.Customer
import kotlinx.serialization.Serializable

@Serializable
data class CustomerLocalDTO(
    val id: String,
    val name: String,
    val phone: String
) {
    fun toDomain() = Customer(
        id = id,
        name = name,
        phone = phone
    )
}