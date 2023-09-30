package dev.hicka04.pokedex.core.network.response

import dev.hicka04.pokedex.core.network.response.common.NamedResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonListResponse(
    val count: Int,
    val results: List<NamedResponse>
)
