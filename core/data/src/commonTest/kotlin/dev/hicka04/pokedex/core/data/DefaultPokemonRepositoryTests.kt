package dev.hicka04.pokedex.core.data

import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.pokeapi.PokeApi
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class DefaultPokemonRepositoryTests {
    private val pokeApi: PokeApi = mock()

    private lateinit var defaultPokemonRepository: DefaultPokemonRepository

    private val pokemon = Pokemon(
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

    @BeforeTest
    fun setup() {
        defaultPokemonRepository = DefaultPokemonRepository(
            pokeApi = pokeApi
        )
    }

    @Test
    fun getPokemonList_failure() = runTest {
        everySuspend { pokeApi.fetchPokemonList(offset = 0) } throws Exception()

        assertFails { defaultPokemonRepository.getPokemonList(0) }
    }

    @Test
    fun getPokemonList_success() = runTest {
        everySuspend { pokeApi.fetchPokemonList(offset = 0) } returns listOf(pokemon)

        assertEquals(
            defaultPokemonRepository.getPokemonList(0),
            listOf(pokemon)
        )
    }

    @Test
    fun getPokemon_failure() = runTest {
        everySuspend { pokeApi.fetchPokemon(name = "bulbasaur") } throws Exception()

        assertFails { defaultPokemonRepository.getPokemon("bulbasaur") }
    }

    @Test
    fun getPokemon_success() = runTest {
        everySuspend { pokeApi.fetchPokemon(name = "bulbasaur") } returns pokemon

        assertEquals(
            defaultPokemonRepository.getPokemon("bulbasaur"),
            pokemon
        )
    }
}