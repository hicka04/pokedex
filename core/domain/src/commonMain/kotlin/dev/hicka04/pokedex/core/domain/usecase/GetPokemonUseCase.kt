package dev.hicka04.pokedex.core.domain.usecase

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon

interface GetPokemonUseCase {
    suspend operator fun invoke(name: String): Pokemon
}

class DefaultGetPokemonUseCase(
    private val pokemonRepository: PokemonRepository
): GetPokemonUseCase {
    override suspend fun invoke(name: String): Pokemon =
        pokemonRepository.getPokemon(name = name)
}