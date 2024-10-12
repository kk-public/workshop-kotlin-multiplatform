package dev.community.gdg.baku.kmpbank.android

import dev.community.gdg.baku.kmpbank.di.APP_CONTEXT
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val androidModule = module {
    single(named(APP_CONTEXT)) {
        androidContext()
    }
}