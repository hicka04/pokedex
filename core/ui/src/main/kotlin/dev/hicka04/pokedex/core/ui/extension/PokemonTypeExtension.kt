package dev.hicka04.pokedex.core.ui.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import dev.hicka04.pokedex.core.designsystem.R
import dev.hicka04.pokedex.core.model.Pokemon

@Composable
fun Pokemon.Type.color(): Color = colorResource(
    when (this) {
        Pokemon.Type.NORMAL -> R.color.type_normal
        Pokemon.Type.FIRE -> R.color.type_fire
        Pokemon.Type.WATER -> R.color.type_water
        Pokemon.Type.ELECTRIC -> R.color.type_electric
        Pokemon.Type.GRASS -> R.color.type_grass
        Pokemon.Type.ICE -> R.color.type_ice
        Pokemon.Type.FIGHTING -> R.color.type_fighting
        Pokemon.Type.POISON -> R.color.type_poison
        Pokemon.Type.GROUND -> R.color.type_ground
        Pokemon.Type.FLYING -> R.color.type_flying
        Pokemon.Type.PSYCHIC -> R.color.type_psychic
        Pokemon.Type.BUG -> R.color.type_bug
        Pokemon.Type.ROCK -> R.color.type_rock
        Pokemon.Type.GHOST -> R.color.type_ghost
        Pokemon.Type.DRAGON -> R.color.type_dragon
        Pokemon.Type.DARK -> R.color.type_dark
        Pokemon.Type.STEEL -> R.color.type_steel
        Pokemon.Type.FAIRY -> R.color.type_fairy
    }
)

@Composable
fun Pokemon.Type.painter(): Painter = painterResource(
    when (this) {
        Pokemon.Type.NORMAL -> R.drawable.type_normal
        Pokemon.Type.FIRE -> R.drawable.type_fire
        Pokemon.Type.WATER -> R.drawable.type_water
        Pokemon.Type.ELECTRIC -> R.drawable.type_electric
        Pokemon.Type.GRASS -> R.drawable.type_grass
        Pokemon.Type.ICE -> R.drawable.type_ice
        Pokemon.Type.FIGHTING -> R.drawable.type_fighting
        Pokemon.Type.POISON -> R.drawable.type_poison
        Pokemon.Type.GROUND -> R.drawable.type_ground
        Pokemon.Type.FLYING -> R.drawable.type_flying
        Pokemon.Type.PSYCHIC -> R.drawable.type_psychic
        Pokemon.Type.BUG -> R.drawable.type_bug
        Pokemon.Type.ROCK -> R.drawable.type_rock
        Pokemon.Type.GHOST -> R.drawable.type_ghost
        Pokemon.Type.DRAGON -> R.drawable.type_dragon
        Pokemon.Type.DARK -> R.drawable.type_dark
        Pokemon.Type.STEEL -> R.drawable.type_steel
        Pokemon.Type.FAIRY -> R.drawable.type_fairy
    }
)