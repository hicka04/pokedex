package dev.hicka04.pokedex.core.data.di

import dev.hicka04.pokedex.core.data.DefaultPokemonRepository
import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.network.di.networkModule
import org.koin.dsl.module

val dataModule = module {
    includes(networkModule)

    factory<PokemonRepository> { DefaultPokemonRepository(get()) }
}