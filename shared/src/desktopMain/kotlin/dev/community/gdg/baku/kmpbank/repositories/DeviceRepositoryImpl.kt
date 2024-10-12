package dev.community.gdg.baku.kmpbank.repositories

import dev.community.gdg.baku.kmpbank.domain.entities.Platform
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository

class DeviceRepositoryImpl : DeviceRepository {

    override fun datastorePath(fileName: String): String {
        return "$fileName.preferences_pb"
    }

    override fun getPlatform(): Platform {
        return Platform(
            deviceName = "Java ${System.getProperty("java.version")}",
            osName = "Desktop"
        )
    }
}