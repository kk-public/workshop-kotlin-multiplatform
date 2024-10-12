package ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.community.gdg.baku.kmpbank.domain.exceptions.ClientError
import dev.community.gdg.baku.kmpbank.domain.usecases.base.BaseAsyncUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    protected fun <T> Flow<T>.launch(
        scope: CoroutineScope = viewModelScope
    ): Job = this.launchIn(scope)

    fun <Output> launchRoutine(
        block: suspend () -> Output,
        result: AsyncJobCompletionBlock<Output> = {},
    ): Job {
        return launchRoutineWithLoading(block, updateLoadingStatus = {}, result)
    }

    fun <Output> launchRoutineWithLoading(
        block: suspend () -> Output,
        updateLoadingStatus: (Boolean) -> Unit = { isLoading = it },
        result: AsyncJobCompletionBlock<Output> = {},
    ): Job {
        return viewModelScope.launch {
            val job = AsyncJob<Output>().apply(result).also { it.onStart?.invoke() }

            updateLoadingStatus(true)

            try {
                val response = block()
                job.onSuccess(response)
            } catch (e: Throwable) {
                job.onError?.invoke(e) ?: run {
                    if (e is ClientError.General) {
                        // todo map to real message
                    }
                }
            }
            updateLoadingStatus(false)
            job.onComplete?.invoke()
        }
    }

    protected fun <Input, Output, UseCase : BaseAsyncUseCase<Input, Output>> UseCase.launch(
        param: Input,
        result: AsyncJobCompletionBlock<Output> = {},
    ): Job {
        return launchWithLoading(param, updateLoadingStatus = {}, result)
    }

    protected fun <Input, Output, UseCase : BaseAsyncUseCase<Input, Output>> UseCase.launchWithLoading(
        param: Input,
        updateLoadingStatus: (Boolean) -> Unit = { isLoading = it },
        result: AsyncJobCompletionBlock<Output> = {},
    ): Job {
        return launchRoutineWithLoading({ execute(param) }, updateLoadingStatus, result)
    }
}

typealias AsyncJobCompletionBlock<T> = AsyncJob<T>.() -> Unit

class AsyncJob<T> {
    var onSuccess: (T) -> Unit = {}
    var onStart: (() -> Unit)? = null
    var onComplete: (() -> Unit)? = null
    var onError: ((Throwable) -> Unit)? = null
}