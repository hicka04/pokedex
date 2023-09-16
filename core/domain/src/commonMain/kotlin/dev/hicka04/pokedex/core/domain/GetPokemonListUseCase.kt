package dev.hicka04.pokedex.core.domain

import dev.hicka04.pokedex.core.model.Pokemon

class GetPokemonListUseCase {
    suspend operator fun invoke(): List<Pokemon> {
        return listOf(
            Pokemon(1, "Bulbasaur"),
            Pokemon(2, "Ivysaur"),
            Pokemon(3, "Venusaur"),
        )
    }
}

