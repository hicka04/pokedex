package dev.hicka04.pokedex.core.network

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val results: List<Item>
) {
    @Serializable
    data class Item(
        val name: String,
    )
}
