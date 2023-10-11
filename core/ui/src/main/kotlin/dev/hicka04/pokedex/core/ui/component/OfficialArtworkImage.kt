package dev.hicka04.pokedex.core.ui.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun OfficialArtworkImage(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "official artwork",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}

@Preview
@Composable
fun OfficialArtworkImagePreview() {
    OfficialArtworkImage(url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
}