package dev.community.gdg.baku.kmpbank.data.tools

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> String.decodeJson() = Json.decodeFromString<T>(this)

inline fun <reified T> T.encodeJson() = Json.encodeToString(this)