package dev.community.gdg.baku.kmpbank.domain.repositories

import dev.community.gdg.baku.kmpbank.domain.entities.Platform

interface DeviceRepository {
    fun datastorePath(fileName: String): String
    fun getPlatform(): Platform
}