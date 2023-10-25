package dev.hicka04.pokedex.core.domain.repository

import dev.hicka04.pokedex.core.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int): List<Pokemon>
}