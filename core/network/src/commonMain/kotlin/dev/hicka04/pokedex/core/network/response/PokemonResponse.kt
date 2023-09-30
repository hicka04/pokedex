package dev.hicka04.pokedex.core.network.response

import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: String,
) {
    fun toEntity() = Pokemon(
        id = id,
        name = name,
        types = Pokemon.Types(
            first = Pokemon.Type.valueOf("grass".uppercase()),
            second = Pokemon.Type.valueOf("poison".uppercase())
        )
    )
}
