package dev.hicka04.pokedex.core.domain

import dev.hicka04.pokedex.core.domain.repository.PokemonRepository
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListInteractor
import dev.hicka04.pokedex.core.domain.usecase.GetPokemonListUseCase
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
class GetPokemonListUseCaseTests : TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var pokemonRepository: PokemonRepository

    private val getPokemonListUseCase: GetPokemonListUseCase by withMocks {
        GetPokemonListInteractor(pokemonRepository = pokemonRepository)
    }

    @Test
    fun invoke_failure() = runTest {
        everySuspending { pokemonRepository.getPokemonList(0) } runs { throw Exception() }

        assertFails { getPokemonListUseCase(0) }
    }

    @Test
    fun invoke_success() = runTest {
        val fakePokemon = fakePokemon()
        everySuspending { pokemonRepository.getPokemonList(0) } returns listOf(fakePokemon)

        assertEquals(
            getPokemonListUseCase(0),
            listOf(fakePokemon)
        )
    }
}