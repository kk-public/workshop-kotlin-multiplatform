package dev.community.gdg.baku.kmpbank.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.AndroidSQLiteDriver
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardDatabase
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository
import dev.community.gdg.baku.kmpbank.repositories.DeviceRepositoryImpl
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APP_CONTEXT = "app-context"

val androidSharedModule = module {
    factory<HttpClientEngineFactory<HttpClientEngineConfig>> { OkHttp }

    factory<DeviceRepository> { DeviceRepositoryImpl(get(named(APP_CONTEXT))) }

    factory<RoomDatabase.Builder<CardDatabase>>(named("card-db")) {
        val context = get<Context>(named(APP_CONTEXT))
        val dbFile = context.getDatabasePath("cards.db")
        return@factory Room.databaseBuilder<CardDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
    }

    factory<SQLiteDriver> {
        AndroidSQLiteDriver()
    }
}