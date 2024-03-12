package dev.hicka04.pokedex.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.hicka04.pokedex.feature.pokemon_detail.PokemonDetailScreen
import dev.hicka04.pokedex.feature.pokemon_list.PokemonListScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            MainAppBar(navController)
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "pokemon",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(route = "pokemon") {
                    PokemonListScreen(
                        onClickPokemon = { pokemon ->
                            navController.navigate("pokemon/${pokemon.name}")
                        }
                    )
                }

                composable(
                    route = "pokemon/{name}",
                    arguments = listOf(
                        navArgument("name") {
                            type = NavType.StringType
                            nullable = false
                        }
                    )
                ) {
                    PokemonDetailScreen()
                }
            }
        }
    )
}

@Composable
fun MainAppBar(navController: NavController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val arguments = backStackEntry?.arguments

    TopAppBar(
        title = {
            Text(
                when (currentRoute) {
                    "pokemon" -> "Pokedex"
                    "pokemon/{name}" -> arguments?.getString("name")!!
                    else -> ""
                }
            )
        },
        navigationIcon = navController.previousBackStackEntry?.let {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}