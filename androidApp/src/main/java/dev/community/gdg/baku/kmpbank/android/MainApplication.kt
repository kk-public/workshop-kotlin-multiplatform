package dev.community.gdg.baku.kmpbank.android

import android.app.Application
import dev.community.gdg.baku.kmpbank.commonModule
import dev.community.gdg.baku.kmpbank.di.androidSharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)

            modules(commonModule() + androidSharedModule + androidModule)
        }
    }
}