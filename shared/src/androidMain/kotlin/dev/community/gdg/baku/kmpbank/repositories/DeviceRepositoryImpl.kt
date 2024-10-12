package dev.community.gdg.baku.kmpbank.repositories

import android.content.Context
import dev.community.gdg.baku.kmpbank.domain.entities.Platform
import dev.community.gdg.baku.kmpbank.domain.repositories.DeviceRepository

class DeviceRepositoryImpl(private val context: Context) : DeviceRepository {

    override fun datastorePath(fileName: String): String {
        return context.filesDir.resolve("$fileName.preferences_pb").absolutePath
    }

    override fun getPlatform(): Platform {
        return Platform(
            deviceName = "Android ${android.os.Build.VERSION.SDK_INT}",
            osName = "Android"
        )
    }
}