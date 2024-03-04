package dev.hicka04.pokedex.core.domain.usecase

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import org.koin.core.annotation.Factory
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

interface GetPokemonUseCase {
    @OptIn(ExperimentalObjCName::class)
    @ObjCName("callAsFunction")
    @Throws(Exception::class)
    suspend operator fun invoke(name: String): Pokemon
}

@Factory
class GetPokemonInteractor(
    private val pokemonRepository: PokemonRepository
): GetPokemonUseCase {
    override suspend fun invoke(name: String): Pokemon =
        pokemonRepository.getPokemon(name = name)
}