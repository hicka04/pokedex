package dev.hicka04.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform