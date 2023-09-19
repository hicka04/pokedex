package dev.hicka04.pokedex.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.hicka04.pokedex.core.designsystem.PokedexTheme
import dev.hicka04.pokedex.feature.pokemon_list.PokemonListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                PokemonListScreen()
            }
        }
    }
}
