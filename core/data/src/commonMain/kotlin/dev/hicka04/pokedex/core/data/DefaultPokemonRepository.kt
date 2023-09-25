package dev.hicka04.pokedex.core.data

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.coroutines.delay

class DefaultPokemonRepository: PokemonRepository {
    override suspend fun getPokemonList(): List<Pokemon> {
        delay(1000)
        return listOf(
            Pokemon(1, "Bulbasaur"),
            Pokemon(2, "Ivysaur"),
            Pokemon(3, "Venusaur"),
        )
    }
}