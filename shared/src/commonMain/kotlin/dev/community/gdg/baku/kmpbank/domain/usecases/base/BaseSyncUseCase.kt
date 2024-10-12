package dev.community.gdg.baku.kmpbank.domain.usecases.base

abstract class BaseSyncUseCase<Input, Output> {

    protected abstract fun executeRoutine(params: Input): Output

    @Throws(
        Throwable::class,
    )
    fun execute(params: Input): Output = executeRoutine(params)
}