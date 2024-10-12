package dev.community.gdg.baku.kmpbank.domain.usecases.auth

import dev.community.gdg.baku.kmpbank.domain.entities.AuthState
import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase

class GetAuthStateUseCase(
    private val repo: AuthRepository,
) : BaseAsyncUseCase<Unit, AuthState>() {

    override suspend fun executeRoutine(params: Unit): AuthState {
        return when (repo.isAuthorized()) {
            true -> AuthState.AUTHORIZED
            false -> AuthState.UNAUTHORIZED
        }
    }
}