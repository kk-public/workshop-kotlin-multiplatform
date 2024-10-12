package dev.community.gdg.baku.kmpbank.data.repositories.auth.remote.dto

import dev.community.gdg.baku.kmpbank.data.repositories.auth.local.dto.CustomerLocalDTO
import kotlinx.serialization.Serializable

@Serializable
data class CustomerRemoteDTO(
    val id: String,
    val name: String,
    val phone: String
) {
    fun toLocal() = CustomerLocalDTO(
        id = id,
        name = name,
        phone = phone
    )
}