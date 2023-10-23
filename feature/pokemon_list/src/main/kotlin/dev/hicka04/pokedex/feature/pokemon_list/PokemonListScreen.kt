package dev.hicka04.pokedex.feature.pokemon_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
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
import dev.hicka04.pokedex.core.ui.extension.icon
import dev.hicka04.pokedex.core.ui.extension.plus
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel(),
    onClickPokemon: (Pokemon) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonListScreen(
        uiState = uiState,
        onClickPokemon = onClickPokemon
    )
}

@Composable
fun PokemonListScreen(
    uiState: PokemonListUiState,
    onClickPokemon: (Pokemon) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokedex") })
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(paddingValues + PaddingValues(horizontal = 16.dp)),
            ) {
                items(uiState.pokemonList) { pokemon ->
                    Column(modifier = Modifier.clickable { onClickPokemon(pokemon) }) {
                        OfficialArtworkImage(url = pokemon.sprites.officialArtwork)

                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "No.${pokemon.id}",
                                    style = MaterialTheme.typography.caption
                                )
                                Text(
                                    text = pokemon.name,
                                    style = MaterialTheme.typography.h6
                                )
                            }

                            Column {
                                pokemon.types.first.icon()
                                pokemon.types.second?.icon()
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
            ),
            onClickPokemon = {}
        )
    }
}