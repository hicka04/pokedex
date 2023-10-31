package dev.hicka04.pokedex.core.network.di

import dev.hicka04.pokedex.core.network.DefaultPokeApi
import dev.hicka04.pokedex.core.network.PokeApi
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    includes(ktorEngineModule)

    singleOf(::DefaultPokeApi) bind PokeApi::class
}

expect val ktorEngineModule: Module