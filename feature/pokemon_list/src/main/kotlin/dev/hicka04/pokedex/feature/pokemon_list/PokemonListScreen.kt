package dev.hicka04.pokedex.feature.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import dev.hicka04.pokedex.core.designsystem.PokedexTheme
import dev.hicka04.pokedex.core.model.Pokemon
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel()
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
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(text = "No.${it.id}")

                        Column {
                            Text(text = it.name)
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = it.types.first.name)
                                it.types.second?.let {
                                    Text(text = it.name)
                                }
                            }
                        }
                    }
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
                    Pokemon(
                        id = 1,
                        name = "Bulbasaur",
                        types = Pokemon.Types(
                            first = Pokemon.Type.GRASS,
                            second = Pokemon.Type.POISON
                        )
                    ),
                    Pokemon(
                        id = 2,
                        name = "Ivysaur",
                        types = Pokemon.Types(
                            first = Pokemon.Type.GRASS,
                            second = Pokemon.Type.POISON
                        )
                    ),
                    Pokemon(
                        id = 3,
                        name = "Venusaur",
                        types = Pokemon.Types(
                            first = Pokemon.Type.GRASS,
                            second = Pokemon.Type.POISON
                        )
                    ),
                ),
            )
        )
    }
}