package dev.hicka04.pokedex.shared

import dev.hicka04.pokedex.core.domain.di.domainModule
import org.koin.core.context.startKoin

fun sharedModules() = listOf(domainModule)
fun initKoin() = startKoin {
    modules(sharedModules())
}