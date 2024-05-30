package dev.hicka04.pokedex.core.domain

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonInteractor
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonUseCase
import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.model.fakePokemon
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.UsesFakes
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

@UsesFakes(Pokemon::class)
class GetPokemonUseCaseTests : TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var pokemonRepository: PokemonRepository

    private val getPokemonUseCase: GetPokemonUseCase by withMocks {
        GetPokemonInteractor(pokemonRepository = pokemonRepository)
    }

    @Test
    fun invoke_failure() = runTest {
        everySuspending { pokemonRepository.getPokemon(name = "bulbasaur") } runs { throw Exception() }

        assertFails { getPokemonUseCase(name = "bulbasaur") }
    }

    @Test
    fun invoke_success() = runTest {
        val fakePokemon = fakePokemon()
        everySuspending { pokemonRepository.getPokemon(name = "bulbasaur") } returns fakePokemon

        assertEquals(
            getPokemonUseCase(name = "bulbasaur"),
            fakePokemon
        )
    }
}