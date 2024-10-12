package dev.community.gdg.baku.kmpbank.domain.usecases.base

import kotlinx.coroutines.flow.Flow

abstract class BaseObservableUseCase<Input, Output> {

    protected abstract fun observeOn(params: Input): Flow<Output>

    fun observe(params: Input): Flow<Output> = observeOn(params)
}