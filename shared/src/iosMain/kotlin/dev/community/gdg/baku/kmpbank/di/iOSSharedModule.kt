package dev.community.gdg.baku.kmpbank.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.NativeSQLiteDriver
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardDatabase
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository
import dev.community.gdg.baku.kmpbank.repositories.DeviceRepositoryImpl
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

val iOSSharedModule = module {
    factory<HttpClientEngineFactory<HttpClientEngineConfig>> { Darwin }

    factory<DeviceRepository> { DeviceRepositoryImpl() }

    factory<RoomDatabase.Builder<CardDatabase>>(named("card-db")) {
        val dbFile = documentDirectory() + "/cards.db"
        Room.databaseBuilder<CardDatabase>(
            name = dbFile
        )
    }

    factory<RoomDatabase.Builder<CardDatabase>>(named("transaction-db")) {
        val dbFile = documentDirectory() + "/transactions.db"
        Room.databaseBuilder<CardDatabase>(
            name = dbFile
        )
    }

    factory<SQLiteDriver> {
        NativeSQLiteDriver()
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}