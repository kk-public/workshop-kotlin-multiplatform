package dev.community.gdg.baku.kmpbank

import dev.community.gdg.baku.kmpbank.data.di.dataModule
import dev.community.gdg.baku.kmpbank.domain.di.domainModule
import ui.di.appModule

fun commonModule() = listOf(domainModule, dataModule, appModule)