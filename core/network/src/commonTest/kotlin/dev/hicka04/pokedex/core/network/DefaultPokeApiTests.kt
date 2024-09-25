package dev.hicka04.pokedex.core.network

import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.pokeapi.DefaultPokeApi
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class DefaultPokeApiTests {
    private val defaultPokeApi by lazy { DefaultPokeApi(MockEngine(config)) }
    private val config = MockEngineConfig()

    @AfterTest
    fun tearDown() {
        config.requestHandlers.clear()
    }

    @Test
    fun fetchPokemonList_serverError() = runTest {
        config.addHandler {
            respondError(
                status = HttpStatusCode.InternalServerError
            )
        }

        assertFails { defaultPokeApi.fetchPokemonList(offset = 0) }
    }

    @Test
    fun fetchPokemonList_parseError() = runTest {
        config.addHandler {
            respond(
                """
                {
                    "hoge": "fuga"
                }
                """.trimIndent(),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        assertFails { defaultPokeApi.fetchPokemonList(offset = 0) }
    }

    @Test
    fun fetchPokemonList_success() = runTest {
        config.addHandler { request ->
            if (request.url.encodedPath.contains("pokemon/bulbasaur")) {
                respond(
                    """
                    {
                        "id": 1,
                        "name": "bulbasaur",
                        "types": [
                            {
                                "slot": 1,
                                "type": {
                                    "name": "grass"
                                }
                            },
                            {
                                "slot": 2,
                                "type": {
                                    "name": "poison"
                                }
                            }
                        ],
                        "sprites": {
                            "other": {
                                "official-artwork": {
                                    "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                                }
                            }
                        }
                    }
                    """.trimIndent(),
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                respond(
                    """
                    {
                        "count":1000,
                        "results": [
                            {
                                "name": "bulbasaur"
                            }
                        ]
                    }
                    """.trimIndent(),
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }

        assertEquals(
            defaultPokeApi.fetchPokemonList(offset = 0),
            listOf(
                Pokemon(
                    id = 1,
                    name = "bulbasaur",
                    types = Pokemon.Types(
                        first = Pokemon.Type.GRASS,
                        second = Pokemon.Type.POISON
                    ),
                    sprites = Pokemon.Sprites(
                        officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                    )
                )
            )
        )
    }

    @Test
    fun fetchPokemon_serverError() = runTest {
        config.addHandler {
            respondError(
                status = HttpStatusCode.InternalServerError
            )
        }

        assertFails { defaultPokeApi.fetchPokemon(name = "bulbasaur") }
    }

    @Test
    fun fetchPokemon_parseError() = runTest {
        config.addHandler {
            respond(
                """
                {
                    "hoge": "fuga"
                }
                """.trimIndent(),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        assertFails { defaultPokeApi.fetchPokemon(name = "bulbasaur") }
    }

    @Test
    fun fetchPokemon_success() = runTest {
        config.addHandler {
            respond(
                """
                {
                    "id": 1,
                    "name": "bulbasaur",
                    "types": [
                        {
                            "slot": 1,
                            "type": {
                                "name": "grass"
                            }
                        },
                        {
                            "slot": 2,
                            "type": {
                                "name": "poison"
                            }
                        }
                    ],
                    "sprites": {
                        "other": {
                            "official-artwork": {
                                "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                            }
                        }
                    }
                }
                """.trimIndent(),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        assertEquals(
            defaultPokeApi.fetchPokemon(name = "bulbasaur"),
            Pokemon(
                id = 1,
                name = "bulbasaur",
                types = Pokemon.Types(
                    first = Pokemon.Type.GRASS,
                    second = Pokemon.Type.POISON
                ),
                sprites = Pokemon.Sprites(
                    officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        )
    }
}