package dev.hicka04.pokedex.core.data

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.PokeApi

class DefaultPokemonRepository(
    private val pokeApi: PokeApi
): PokemonRepository {
    override suspend fun getPokemonList(): List<Pokemon> =
        pokeApi.fetchPokemonList()
}