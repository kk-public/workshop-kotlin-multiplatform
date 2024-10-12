package dev.community.gdg.baku.kmpbank.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteDriver
import dev.community.gdg.baku.kmpbank.data.exceptions.RemoteClientErrorMapper
import dev.community.gdg.baku.kmpbank.data.repositories.auth.AuthRepositoryImpl
import dev.community.gdg.baku.kmpbank.data.repositories.auth.local.AuthLocalData
import dev.community.gdg.baku.kmpbank.data.repositories.auth.local.AuthLocalDataImpl
import dev.community.gdg.baku.kmpbank.data.repositories.auth.remote.AuthRemoteData
import dev.community.gdg.baku.kmpbank.data.repositories.auth.remote.AuthRemoteDataImpl
import dev.community.gdg.baku.kmpbank.data.repositories.card.CardRepositoryImpl
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardDatabase
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardLocalData
import dev.community.gdg.baku.kmpbank.data.repositories.card.local.CardLocalDataImpl
import dev.community.gdg.baku.kmpbank.data.repositories.card.remote.CardRemoteData
import dev.community.gdg.baku.kmpbank.data.repositories.card.remote.CardRemoteDataImpl
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.TransactionRepositoryImpl
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.TransactionLocalData
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.local.TransactionLocalDataImpl
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.remote.TransactionRemoteData
import dev.community.gdg.baku.kmpbank.data.repositories.transaction.remote.TransactionRemoteDataImpl
import dev.community.gdg.baku.kmpbank.data.tools.setup
import dev.community.gdg.baku.kmpbank.domain.repositories.AuthRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.CardRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository
import dev.community.gdg.baku.kmpbank.domain.repositories.TransactionRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.logging.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import okio.Path.Companion.toPath
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val HTTP_HOST = "6214983b89fad53b1f18329c.mockapi.io"

val dataModule = module {

    /**
     * HTTP
     */

    factory {
        val engine: HttpClientEngineFactory<HttpClientEngineConfig> = get()

        val client = HttpClient(engine) {
            val deviceRepo = get<DeviceRepository>()
            val platform = deviceRepo.getPlatform()
            this.setup(
                host = HTTP_HOST,
                platform = platform,
                errorMapper = get(),
                jsonParser = get(),
                customLogger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            )
        }

        return@factory client
    }

    factory { RemoteClientErrorMapper() }

    factory {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            encodeDefaults = true
        }
    }

    /**
     * Local datastore
     */

    factory<DataStore<Preferences>> { fileName ->
        val deviceRepository = get<DeviceRepository>()
        val localPath = deviceRepository.datastorePath(fileName[0])
        PreferenceDataStoreFactory.createWithPath(produceFile = {
            localPath.toPath()
        })
    }

    /**
     * Auth
     */

    single<AuthLocalData> {
        val dataStore = get<DataStore<Preferences>>(parameters = { parametersOf("auth") })
        AuthLocalDataImpl(
            dataStore = dataStore
        )
    }

    factory<AuthRemoteData> { AuthRemoteDataImpl(get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    /**
     * Card
     */

    single<CardDatabase> {
        val builder = get<RoomDatabase.Builder<CardDatabase>>(named("card-db"))
        val driver = get<SQLiteDriver>()
        builder
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(driver)
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single<CardLocalData> {
        val cardDAO = get<CardDatabase>().cardDao()
        CardLocalDataImpl(
            cardDAO = cardDAO
        )
    }

    factory<CardRemoteData> { CardRemoteDataImpl(get()) }

    factory<CardRepository> { CardRepositoryImpl(get(), get()) }

    /**
     * Transaction
     */

    single<TransactionLocalData> {
        val transactionDAO = get<CardDatabase>().transactionDAO()
        TransactionLocalDataImpl(transactionDAO)
    }

    factory<TransactionRemoteData> { TransactionRemoteDataImpl(get()) }

    factory<TransactionRepository> { TransactionRepositoryImpl(get(), get()) }
}