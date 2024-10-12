package dev.community.gdg.baku.kmpbank.domain.di

import dev.community.gdg.baku.kmpbank.domain.usecases.auth.AuthorizeUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.GetAuthStateUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.GetCustomerUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.LogoutUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.ObserveCustomerUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.auth.SyncCustomerUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.card.GetCardsUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.card.ObserveCardsUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.card.SyncCardsUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.transaction.ObserveTransactionsByCardUseCase
import dev.community.gdg.baku.kmpbank.domain.usecases.transaction.SyncTransactionsByCardUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { AuthorizeUseCase(get()) }
    factory { LogoutUseCase(get(), get(), get()) }
    factory { ObserveCardsUseCase(get()) }
    factory { SyncCardsUseCase(get(), get()) }
    factory { ObserveTransactionsByCardUseCase(get()) }
    factory { SyncTransactionsByCardUseCase(get(), get()) }
    factory { GetCustomerUseCase(get()) }
    factory { SyncCustomerUseCase(get()) }
    factory { ObserveCustomerUseCase(get()) }
    factory { GetAuthStateUseCase(get()) }
    factory { GetCardsUseCase(get()) }
}