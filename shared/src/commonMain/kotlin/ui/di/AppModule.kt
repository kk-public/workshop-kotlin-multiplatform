package ui.di

import org.koin.core.module.dsl.viewModelOf
import ui.flows.auth.AuthViewModel
import ui.flows.main.MainViewModel
import ui.flows.app.AppViewModel
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::AppViewModel)
}