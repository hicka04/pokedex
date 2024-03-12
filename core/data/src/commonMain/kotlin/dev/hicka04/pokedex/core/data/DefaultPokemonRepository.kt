package dev.hicka04.pokedex.core.data

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.pokeapi.PokeApi
import org.koin.core.annotation.Factory

@Factory
class DefaultPokemonRepository(
    private val pokeApi: PokeApi
): PokemonRepository {
    override suspend fun getPokemonList(offset: Int): List<Pokemon> =
        pokeApi.fetchPokemonList(offset = offset)
}