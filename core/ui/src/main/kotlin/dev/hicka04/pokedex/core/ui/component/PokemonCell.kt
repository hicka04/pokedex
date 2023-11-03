package dev.hicka04.pokedex.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hicka04.pokedex.core.model.Pokemon
import dev.hicka04.pokedex.core.ui.extension.icon

@Composable
fun PokemonCell(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
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

@Preview
@Composable
fun PokemonCellPreview() {
    PokemonCell(
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
    )
}