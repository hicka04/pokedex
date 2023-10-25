package dev.hicka04.pokedex.core.network.di

import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val ktorEngineModule = module {
    single { Darwin.create() }
}