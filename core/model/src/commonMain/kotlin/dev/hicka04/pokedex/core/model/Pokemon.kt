package dev.hicka04.pokedex.core.model

data class Pokemon(
    val id: Int,
    val name: String,
    val types: Types,
    val sprites: Sprites,
) {
    data class Types(
        val first: Type,
        val second: Type?
    )

    enum class Type {
        NORMAL,
        FIRE,
        WATER,
        ELECTRIC,
        GRASS,
        ICE,
        FIGHTING,
        POISON,
        GROUND,
        FLYING,
        PSYCHIC,
        BUG,
        ROCK,
        GHOST,
        DRAGON,
        DARK,
        STEEL,
        FAIRY,
        ;
    }

    data class Sprites(
        val officialArtwork: String
    )
}
