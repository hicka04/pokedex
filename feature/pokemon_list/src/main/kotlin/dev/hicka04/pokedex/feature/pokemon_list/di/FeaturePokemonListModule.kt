package dev.hicka04.pokedex.feature.pokemon_list.di

import dev.hicka04.pokedex.feature.pokemon_list.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featurePokemonListModule = module {
    viewModelOf(::PokemonListViewModel)
}