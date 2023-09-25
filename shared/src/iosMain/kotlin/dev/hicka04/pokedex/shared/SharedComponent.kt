package dev.hicka04.pokedex.shared

import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@Suppress("unused")
object SharedComponent: KoinComponent {
    fun getPokemonListUseCase(): GetPokemonListUseCase = get()
}