package dev.community.gdg.baku.kmpbank.data.exceptions

import dev.community.gdg.baku.kmpbank.domain.exceptions.ClientError
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class RemoteClientErrorMapper {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun mapError(response: HttpResponse): Throwable {
        val exceptionResponseText = response.bodyAsText()

        return try {
            val payload =
                exceptionResponseText.let { json.decodeFromString<BaseErrorResponseDTO>(it) }

            // TODO: Map error codes to specific exceptions
            ClientError.General(payload.code)
        } catch (ex: Throwable) {
            ClientError.Unknown
        }
    }
}