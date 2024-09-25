package dev.hicka04.pokedex.core.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform