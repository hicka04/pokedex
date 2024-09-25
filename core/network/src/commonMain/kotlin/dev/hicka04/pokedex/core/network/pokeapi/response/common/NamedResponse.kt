package dev.hicka04.pokedex.core.network.pokeapi.response.common

import kotlinx.serialization.Serializable

@Serializable
internal data class NamedResponse(
    val name: String
)
