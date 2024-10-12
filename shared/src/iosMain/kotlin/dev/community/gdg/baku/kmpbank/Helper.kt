package dev.community.gdg.baku.kmpbank

import dev.community.gdg.baku.kmpbank.di.iOSSharedModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(commonModule() + iOSSharedModule)
    }
}