package dev.hicka04.pokedex.feature.pokemon_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonDetailScreen(name: String) {
    Text(
        text = name,
        modifier = Modifier.fillMaxSize()
    )
}