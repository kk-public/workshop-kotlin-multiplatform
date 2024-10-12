package dev.community.gdg.baku.kmpbank.domain.usecases.auth

import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class AuthorizeUseCase(
    private val repo: AuthRepository,
) : BaseAsyncUseCase<AuthorizeUseCase.Params, Unit>() {

    override suspend fun executeRoutine(params: Params) {
        // TODO: implement
        throw NotImplementedError("Not implemented")
    }

    data class Params(
        val email: String,
        val password: String
    )
}