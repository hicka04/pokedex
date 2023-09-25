package dev.hicka04.pokedex.core.data.di

import dev.hicka04.pokedex.core.data.DefaultPokemonRepository
import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    factoryOf<PokemonRepository>(::DefaultPokemonRepository)
}