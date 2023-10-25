package dev.hicka04.pokedex.core.network

import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.network.response.PokemonListResponse
import dev.hicka04.pokedex.core.network.response.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json

class PokeApi {
    private val baseUrl = "https://pokeapi.co/api/v2"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun fetchPokemonList(offset: Int): List<Pokemon> =
        client.get("$baseUrl/pokemon?offset=$offset&limit=60")
            .body<PokemonListResponse>()
            .results
            .mapAsync { fetchPokemon(it.name) }

    private suspend fun fetchPokemon(name: String): Pokemon =
        client.get("$baseUrl/pokemon/$name")
            .body<PokemonResponse>()
            .toEntity()
}

suspend fun <T, R> Iterable<T>.mapAsync(transform: suspend (T) -> R): List<R> =
    coroutineScope {
        map { async { transform(it) } }
            .awaitAll()
    }