package dev.hicka04.pokedex.feature.pokemon_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.hicka04.pokedex.core.designsystem.PokedexTheme
import dev.hicka04.pokedex.core.model.Pokemon

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonListScreen(uiState = uiState)
}

@Composable
fun PokemonListScreen(
    uiState: PokemonListUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokedex") })
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(uiState.pokemonList) {
                    Text(text = it.name)
                }
            }
        }
    )
}

@Preview
@Composable
fun PokemonListScreenPreview() {
    PokedexTheme {
        PokemonListScreen(
            uiState = PokemonListUiState(
                pokemonList = listOf(
                    Pokemon(1, "Bulbasaur"),
                    Pokemon(2, "Ivysaur"),
                    Pokemon(3, "Venusaur"),
                ),
            )
        )
    }
}