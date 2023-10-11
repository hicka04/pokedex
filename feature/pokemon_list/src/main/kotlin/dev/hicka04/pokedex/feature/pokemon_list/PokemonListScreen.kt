package dev.hicka04.pokedex.feature.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
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
import dev.hicka04.pokedex.core.ui.component.OfficialArtworkImage
import dev.hicka04.pokedex.core.ui.extension.color
import dev.hicka04.pokedex.core.ui.extension.painter
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
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                state = rememberLazyListState()
            ) {
                items(uiState.pokemonList) { pokemon ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        OfficialArtworkImage(url = pokemon.sprites.officialArtwork)

                        Column {
                            Text(text = "No.${pokemon.id}")
                            Text(text = pokemon.name)
                        }

                        Row {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                pokemon.types.first.let { type ->
                                    Icon(
                                        painter = type.painter(),
                                        contentDescription = type.name,
                                        tint = type.color()
                                    )
                                    Text(text = type.name)
                                }

                                pokemon.types.second?.let { type ->
                                    Icon(
                                        painter = type.painter(),
                                        contentDescription = type.name,
                                        tint = type.color()
                                    )
                                    Text(text = type.name)
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
                        ),
                        sprites = Pokemon.Sprites(
                            officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                        ),
                    ),
                    Pokemon(
                        id = 2,
                        name = "Ivysaur",
                        types = Pokemon.Types(
                            first = Pokemon.Type.GRASS,
                            second = Pokemon.Type.POISON
                        ),
                        sprites = Pokemon.Sprites(
                            officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
                        ),
                    ),
                    Pokemon(
                        id = 3,
                        name = "Venusaur",
                        types = Pokemon.Types(
                            first = Pokemon.Type.GRASS,
                            second = Pokemon.Type.POISON
                        ),
                        sprites = Pokemon.Sprites(
                            officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
                        ),
                    ),
                ),
            )
        )
    }
}