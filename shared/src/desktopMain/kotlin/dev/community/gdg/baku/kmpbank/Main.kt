package dev.community.gdg.baku.kmpbank

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.community.gdg.baku.kmpbank.di.desktopModule
import org.koin.core.context.GlobalContext.startKoin
import ui.flows.app.AppView

fun main() = application {
    startKoin {
        modules(commonModule() + desktopModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "GDG Bank KMP"
    ) {
        AppView()
    }
}