package dev.hicka04.pokedex.core.network.response.common

import kotlinx.serialization.Serializable

@Serializable
internal data class NamedResponse(
    val name: String
)
