package dev.hicka04.pokedex.shared

import dev.hicka04.pokedex.core.data.di.dataModule
import dev.hicka04.pokedex.core.domain.di.domainModule
import org.koin.core.context.startKoin

fun sharedModules() = listOf(
    domainModule,
    dataModule,
)

@Suppress("unused")
fun initKoin() = startKoin {
    modules(sharedModules())
}