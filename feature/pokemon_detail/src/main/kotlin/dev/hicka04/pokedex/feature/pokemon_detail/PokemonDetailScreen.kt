package dev.hicka04.pokedex.feature.pokemon_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.hicka04.pokedex.core.designsystem.PokedexTheme
import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.ui.component.OfficialArtworkImage
import dev.hicka04.pokedex.core.ui.component.TypeTag
import org.koin.androidx.compose.koinViewModel
import java.lang.Exception

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonDetailScreen(
        pokemon = uiState.pokemon,
        exception = uiState.exception,
        onAppear = viewModel::onAppear,
        onDismissErrorAlert = viewModel::onDismissErrorAlert
    )
}

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon?,
    exception: Exception?,
    onAppear: () -> Unit,
    onDismissErrorAlert: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (pokemon != null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    OfficialArtworkImage(url = pokemon.sprites.officialArtwork)
                }

                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TypeTag(
                            type = pokemon.types.first,
                            modifier = Modifier.weight(1f)
                        )

                        pokemon.types.second?.let {
                            TypeTag(
                                type = it,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }

    if (exception != null) {
        AlertDialog(
            onDismissRequest = onDismissErrorAlert,
            confirmButton = {
                TextButton(onClick = onDismissErrorAlert) {
                    Text(text = "OK")
                }
            },
            title = { Text(text = exception.localizedMessage ?: "Error") }
        )
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
            ),
            exception = null,
            onAppear = {},
            onDismissErrorAlert = {}
        )
    }
}