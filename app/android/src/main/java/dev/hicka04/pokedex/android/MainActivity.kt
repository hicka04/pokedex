package dev.hicka04.pokedex.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.hicka04.pokedex.core.designsystem.PokedexTheme
import dev.hicka04.pokedex.feature.pokemon_detail.PokemonDetailScreen
import dev.hicka04.pokedex.feature.pokemon_list.PokemonListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            PokedexTheme {
                NavHost(
                    navController = navController,
                    startDestination = "pokemon"
                ) {
                    composable(route = "pokemon") {
                        PokemonListScreen(
                            onClickPokemon = { pokemon ->
                                navController.navigate("pokemon/${pokemon.id}")
                            }
                        )
                    }

                    composable(
                        route = "pokemon/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                                nullable = false
                            }
                        )
                    ) { entry ->
                        val id = entry.arguments!!.getInt("id")
                        PokemonDetailScreen(id = id)
                    }
                }
            }
        }
    }
}
