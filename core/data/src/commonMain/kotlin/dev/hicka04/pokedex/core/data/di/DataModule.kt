package dev.hicka04.pokedex.core.data.di

import dev.hicka04.pokedex.core.data.DefaultPokemonRepository
import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.network.NetworkModule
import org.koin.dsl.module
import org.koin.ksp.generated.*

val dataModule = module {
    includes(NetworkModule().module)

    factory<PokemonRepository> { DefaultPokemonRepository(get()) }
}