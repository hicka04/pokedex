package dev.hicka04.pokedex.core.network.di

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val ktorEngineModule = module {
    single { OkHttp.create() }
}