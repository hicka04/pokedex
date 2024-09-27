package dev.hicka04.pokedex.core.domain.usecase

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.model.Pokemon
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class GetPokemonUseCaseTests {
    private val pokemonRepository: PokemonRepository = mock()

    private lateinit var getPokemonUseCase: GetPokemonUseCase

    @BeforeTest
    fun setup() {
        getPokemonUseCase = DefaultGetPokemonUseCase(
            pokemonRepository = pokemonRepository
        )
    }

    @Test
    fun invoke_failure() = runTest {
        everySuspend { pokemonRepository.getPokemon(name = "bulbasaur") } throws Exception()

        assertFails { getPokemonUseCase(name = "bulbasaur") }
    }

    @Test
    fun invoke_success() = runTest {
        val pokemon = Pokemon(
            id = 1,
            name = "bulbasaur",
            types = Pokemon.Types(
                first = Pokemon.Type.GRASS,
                second = Pokemon.Type.POISON
            ),
            sprites = Pokemon.Sprites(
                officialArtwork = ""
            )
        )
        everySuspend { pokemonRepository.getPokemon(name = "bulbasaur") } returns pokemon

        assertEquals(
            getPokemonUseCase(name = "bulbasaur"),
            pokemon
        )
    }
}