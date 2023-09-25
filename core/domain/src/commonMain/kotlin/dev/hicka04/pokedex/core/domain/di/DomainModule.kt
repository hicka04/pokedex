package dev.hicka04.pokedex.core.domain.di

import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListInteractor
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetPokemonListInteractor) bind GetPokemonListUseCase::class
}