package ui.flows.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.community.gdg.baku.kmpbank.domain.entities.AuthState
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.GetAuthStateUseCase
import ui.base.BaseViewModel

class AppViewModel(
    getAuthStateUseCase: GetAuthStateUseCase
) : BaseViewModel() {

    var isAuthorized by mutableStateOf(false)
        private set

    init {
        getAuthStateUseCase.launch(Unit) {
            onSuccess = {
                isAuthorized = it == AuthState.AUTHORIZED
            }
        }
    }

    fun logout() {
        isAuthorized = false
    }

    fun login() {
        isAuthorized = true
    }
}