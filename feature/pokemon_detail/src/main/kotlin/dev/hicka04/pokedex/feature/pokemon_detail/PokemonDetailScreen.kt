package dev.hicka04.pokedex.feature.pokemon_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.hicka04.pokedex.core.designsystem.PokedexTheme
import dev.hicka04.pokedex.core.model.Pokemon
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonDetailScreen(
        uiState = uiState,
        onAppear = viewModel::onAppear
    )
}

@Composable
fun PokemonDetailScreen(
    uiState: PokemonDetailUiState,
    onAppear: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        uiState.pokemon?.let {
            Text(
                text = it.name
            )
        }
    }


    LaunchedEffect(Unit) {
        onAppear()
    }
}

@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            uiState = PokemonDetailUiState(
                pokemon = Pokemon(
                    id = 1,
                    name = "Bulbasaur",
                    types = Pokemon.Types(
                        first = Pokemon.Type.GRASS,
                        second = Pokemon.Type.POISON
                    ),
                    sprites = Pokemon.Sprites(
                        officialArtwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                    ),
                )
            ),
            onAppear = {}
        )
    }
}