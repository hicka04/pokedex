package dev.hicka04.pokedex.core.model

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform