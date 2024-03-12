package dev.hicka04.pokedex.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class NetworkModule actual constructor() {
    @Single
    actual fun httpClientEngine(): HttpClientEngine = Darwin.create()
}