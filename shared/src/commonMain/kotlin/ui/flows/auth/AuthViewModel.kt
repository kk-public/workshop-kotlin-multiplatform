package ui.flows.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.AuthorizeUseCase
import ui.base.BaseViewModel

class AuthViewModel(
    private val authorizeUseCase: AuthorizeUseCase
) : BaseViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var onAuthorized: (() -> Unit)? = null

    fun updateEmail(email: String) {
        this.email = email
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun login() {
        authorizeUseCase.launchWithLoading(AuthorizeUseCase.Params(email, password)) {
            onSuccess = {
                onAuthorized?.invoke()
            }
            onError = {
                println(it)
            }
        }
    }
}