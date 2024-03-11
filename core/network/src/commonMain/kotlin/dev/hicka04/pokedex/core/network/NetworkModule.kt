package dev.hicka04.pokedex.core.network

import io.ktor.client.engine.HttpClientEngine
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
expect class NetworkModule() {
    @Single
    fun httpClientEngine(): HttpClientEngine
}