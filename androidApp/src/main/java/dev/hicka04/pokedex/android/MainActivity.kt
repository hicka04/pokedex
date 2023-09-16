package dev.hicka04.pokedex.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.hicka04.pokedex.core.domain.GetPokemonListUseCase
import dev.hicka04.pokedex.core.model.Pokemon

class MainActivity : ComponentActivity() {
    private val getPokemonListUseCase = GetPokemonListUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                var pokemonList: List<Pokemon> by remember {
                    mutableStateOf(emptyList())
                }
                LaunchedEffect(Unit) {
                    pokemonList = getPokemonListUseCase()
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn {
                        items(pokemonList) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    PokedexTheme {
        GreetingView("Hello, Android!")
    }
}
