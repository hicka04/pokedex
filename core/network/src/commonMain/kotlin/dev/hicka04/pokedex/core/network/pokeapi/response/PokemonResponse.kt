package dev.hicka04.pokedex.core.network.pokeapi.response

import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.pokeapi.response.common.NamedResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonResponse(
    val id: Int,
    val name: String,
    val types: List<Type>,
    val sprites: Sprites,
) {
    @Serializable
    data class Type(
        val slot: Int,
        val type: NamedResponse
    ) {
        fun toEntity() = Pokemon.Type.valueOf(type.name.uppercase())
    }

    @Serializable
    data class Sprites(
        val other: Other
    ) {
        @Serializable
        data class Other(
            @SerialName("official-artwork")
            val officialArtwork: OfficialArtwork
        ) {
            @Serializable
            data class OfficialArtwork(
                @SerialName("front_default")
                val frontDefault: String
            )
        }

        fun toEntity() = Pokemon.Sprites(
            officialArtwork = other.officialArtwork.frontDefault
        )
    }

    fun toEntity() = Pokemon(
        id = id,
        name = name,
        types = Pokemon.Types(
            first = types[0].toEntity(),
            second = types.getOrNull(1)?.toEntity()
        ),
        sprites = sprites.toEntity()
    )
}
