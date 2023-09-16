package dev.hicka04.pokedex.core.domain

import dev.hicka04.pokedex.core.model.Pokemon
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
class GetPokemonListUseCase {
    @ObjCName("callAsFunction")
    suspend operator fun invoke(): List<Pokemon> {
        return listOf(
            Pokemon(1, "Bulbasaur"),
            Pokemon(2, "Ivysaur"),
            Pokemon(3, "Venusaur"),
        )
    }
}

