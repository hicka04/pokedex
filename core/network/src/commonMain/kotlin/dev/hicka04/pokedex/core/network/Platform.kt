package dev.hicka04.pokedex.core.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform