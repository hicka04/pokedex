package dev.hicka04.pokedex.core.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform