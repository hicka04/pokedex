package dev.hicka04.pokedex.core.network.response

import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.response.common.NamedResponse
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<Type>
) {
    @Serializable
    data class Type(
        val slot: Int,
        val type: NamedResponse
    ) {
        fun toEntity() = Pokemon.Type.valueOf(type.name.uppercase())
    }

    fun toEntity() = Pokemon(
        id = id,
        name = name,
        types = Pokemon.Types(
            first = types[0].toEntity(),
            second = types.getOrNull(1)?.toEntity()
        )
    )
}
