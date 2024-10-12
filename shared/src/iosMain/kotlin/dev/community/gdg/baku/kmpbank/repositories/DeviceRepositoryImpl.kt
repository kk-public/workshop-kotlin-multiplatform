package dev.community.gdg.baku.kmpbank.repositories

import dev.community.gdg.baku.kmpbank.domain.entities.Platform
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIDevice

class DeviceRepositoryImpl : DeviceRepository {

    @OptIn(ExperimentalForeignApi::class)
    override fun datastorePath(fileName: String): String {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory).path + "/$fileName.preferences_pb"
    }

    override fun getPlatform(): Platform {
        return Platform(
            deviceName = "${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}",
            osName = "iOS"
        )
    }
}