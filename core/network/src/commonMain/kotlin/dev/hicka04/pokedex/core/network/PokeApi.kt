package dev.hicka04.pokedex.core.network

import dev.hicka04.pokedex.core.model.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
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

    suspend fun fetchPokemonList(): List<Pokemon> =
        client.get("$baseUrl/pokemon")
            .body<PokemonListResponse>()
            .results
            .mapIndexed { index, pokemonResponse -> Pokemon(index, pokemonResponse.name) }
}