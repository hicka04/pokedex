package dev.hicka04.pokedex.core.domain.di

import dev.hicka04.pokedex.core.domain.GetPokemonListInteractor
import dev.hicka04.pokedex.core.domain.GetPokemonListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetPokemonListInteractor) bind GetPokemonListUseCase::class
}