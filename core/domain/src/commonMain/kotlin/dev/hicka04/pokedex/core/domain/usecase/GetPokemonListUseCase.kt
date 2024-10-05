package dev.hicka04.pokedex.core.domain.usecase

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon

interface GetPokemonListUseCase {
    suspend operator fun invoke(offset: Int): List<Pokemon>
}

class DefaultGetPokemonListUseCase(
    private val pokemonRepository: PokemonRepository
): GetPokemonListUseCase {
    override suspend operator fun invoke(offset: Int): List<Pokemon> =
        pokemonRepository.getPokemonList(offset = offset)
}

