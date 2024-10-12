package dev.community.gdg.baku.kmpbank.domain.usecases.base

import kotlin.coroutines.cancellation.CancellationException

abstract class BaseAsyncUseCase<Input, Output> {

    protected abstract suspend fun executeRoutine(params: Input): Output

    @Throws(
        Throwable::class,
        CancellationException::class
    )
    suspend fun execute(params: Input): Output = executeRoutine(params)
}