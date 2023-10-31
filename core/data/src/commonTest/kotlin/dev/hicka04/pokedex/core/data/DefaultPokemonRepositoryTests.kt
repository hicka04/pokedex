package dev.hicka04.pokedex.core.data

import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.model.fakePokemon
import dev.hicka04.pokedex.core.network.PokeApi
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.UsesFakes
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

@UsesFakes(Pokemon::class)
class DefaultPokemonRepositoryTests : TestsWithMocks() {
    override fun setUpMocks() = injectMocks(mocker)

    @Mock lateinit var pokeApi: PokeApi

    private val defaultPokemonRepository by withMocks {
        DefaultPokemonRepository(pokeApi = pokeApi)
    }

    @Test
    fun getPokemonList_failure() = runTest {
        everySuspending { pokeApi.fetchPokemonList(0) } runs { throw Exception() }

        assertFails { defaultPokemonRepository.getPokemonList(0) }
    }

    @Test
    fun getPokemonList_success() = runTest {
        val fakePokemon = fakePokemon()
        everySuspending { pokeApi.fetchPokemonList(0) } returns listOf(fakePokemon)

        assertEquals(
            defaultPokemonRepository.getPokemonList(0),
            listOf(fakePokemon)
        )
    }
}