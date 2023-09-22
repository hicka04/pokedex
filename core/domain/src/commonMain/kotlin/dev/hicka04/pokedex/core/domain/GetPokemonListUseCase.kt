package dev.hicka04.pokedex.core.domain

import dev.hicka04.pokedex.core.model.Pokemon
import kotlinx.coroutines.delay
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

interface GetPokemonListUseCase {
    @OptIn(ExperimentalObjCName::class)
    @ObjCName("callAsFunction")
    suspend operator fun invoke(): List<Pokemon>
}

class GetPokemonListInteractor: GetPokemonListUseCase {
    override suspend operator fun invoke(): List<Pokemon> {
        delay(1000)
        return listOf(
            Pokemon(1, "Bulbasaur"),
            Pokemon(2, "Ivysaur"),
            Pokemon(3, "Venusaur"),
        )
    }
}

