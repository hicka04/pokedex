package dev.hicka04.pokedex.feature.pokemon_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonDetailScreen(id: Int) {
    Scaffold { paddingValues ->
        Text(
            text = "$id",
            modifier = Modifier.padding(paddingValues)
        )
    }
}