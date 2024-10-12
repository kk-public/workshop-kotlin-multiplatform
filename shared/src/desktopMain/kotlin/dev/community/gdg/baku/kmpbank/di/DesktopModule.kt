package dev.community.gdg.baku.kmpbank.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardDatabase
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository
import dev.community.gdg.baku.kmpbank.repositories.DeviceRepositoryImpl
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

val desktopModule = module {
    factory<HttpClientEngineFactory<HttpClientEngineConfig>> { OkHttp }

    factory<DeviceRepository> { DeviceRepositoryImpl() }

    factory<RoomDatabase.Builder<CardDatabase>>(named("card-db")) {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "cards.db")
        return@factory Room.databaseBuilder<CardDatabase>(
            name = dbFile.absolutePath,
        )
    }

    factory<SQLiteDriver> {
        BundledSQLiteDriver()
    }
}