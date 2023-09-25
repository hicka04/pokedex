package dev.hicka04.pokedex.core.network.di

import dev.hicka04.pokedex.core.network.PokeApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::PokeApi)
}