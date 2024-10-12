package dev.community.gdg.baku.kmpbank.domain.exceptions

abstract class HandledException(
    cause: Throwable? = null,
    message: String? = null
) : Throwable(message, cause)

class NetworkError(override val cause: Throwable) : HandledException(cause = cause)

class ServerError(override val cause: Throwable?) : HandledException(cause = cause)

sealed class ClientError : HandledException() {
    data object NotAuthorized : ClientError()
    data object InvalidUserCredentials : ClientError()
    data object CachedCustomerNotFound : ClientError()
    data object Unknown : ClientError()
    data class General(val type: String) : ClientError()
}