package dev.hicka04.pokedex.core.domain.usecase

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import org.koin.core.annotation.Factory
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

interface GetPokemonListUseCase {
    @OptIn(ExperimentalObjCName::class)
    @ObjCName("callAsFunction")
    @Throws(Exception::class)
    suspend operator fun invoke(offset: Int): List<Pokemon>
}

@Factory
class GetPokemonListInteractor(
    private val pokemonRepository: PokemonRepository
): GetPokemonListUseCase {
    override suspend operator fun invoke(offset: Int): List<Pokemon> =
        pokemonRepository.getPokemonList(offset = offset)
}

