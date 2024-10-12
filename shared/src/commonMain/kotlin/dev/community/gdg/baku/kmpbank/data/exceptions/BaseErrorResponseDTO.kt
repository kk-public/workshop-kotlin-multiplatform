package dev.community.gdg.baku.kmpbank.data.exceptions

import kotlinx.serialization.Serializable

@Serializable
data class BaseErrorResponseDTO(
    val code: String
)