package dev.community.gdg.baku.kmpbank.data.tools

import dev.community.gdg.baku.kmpbank.data.exceptions.RemoteClientErrorMapper
import dev.community.gdg.baku.kmpbank.domain.entities.Platform
import dev.community.gdg.baku.kmpbank.domain.exceptions.HandledException
import dev.community.gdg.baku.kmpbank.domain.exceptions.NetworkError
import dev.community.gdg.baku.kmpbank.domain.exceptions.ServerError
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun <T : HttpClientEngineConfig> HttpClientConfig<T>.setup(
    host: String,
    platform: Platform,
    errorMapper: RemoteClientErrorMapper,
    jsonParser: Json,
    customLogger: Logger
) {
    install(UserAgent) {
        agent = "Ktor client mobile - ${platform.osName}"
    }
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            this.host = host
            path("pb/v1/")
        }
    }
    install(HttpRequestRetry) {
        retryOnServerErrors(0)
        exponentialDelay()
    }
    install(ContentNegotiation) {
        json(jsonParser)
    }
    expectSuccess = true
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            throw when (exception) {
                is HandledException -> exception
                is ClientRequestException -> errorMapper.mapError(exception.response)
                is ServerResponseException -> ServerError(exception)
                else -> NetworkError(exception)
            }
        }
    }
//    install(Logging) {
//        logger = customLogger
//        level = LogLevel.ALL
//    }
    install(Logging) {
        // todo implement the logger
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }
}