package dev.hicka04.pokedex.core.network

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonResponse>
)

@Serializable
data class PokemonResponse(
    val name: String,
)
