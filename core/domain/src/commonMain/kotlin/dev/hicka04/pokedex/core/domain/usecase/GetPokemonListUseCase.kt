package dev.hicka04.pokedex.core.domain.usecase

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

interface GetPokemonListUseCase {
    @OptIn(ExperimentalObjCName::class)
    @ObjCName("callAsFunction")
    suspend operator fun invoke(): List<Pokemon>
}

class GetPokemonListInteractor(
    private val pokemonRepository: PokemonRepository
): GetPokemonListUseCase {
    override suspend operator fun invoke(): List<Pokemon> =
        pokemonRepository.getPokemonList()
}

