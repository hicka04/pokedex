package dev.hicka04.pokedex.core.network.response.common

import kotlinx.serialization.Serializable

@Serializable
data class NamedResponse(
    val name: String
)
