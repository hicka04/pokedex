package dev.hicka04.pokedex.shared

import dev.hicka04.pokedex.core.data.DataModule
import dev.hicka04.pokedex.core.domain.di.domainModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

fun sharedModules() = listOf(
    domainModule,
    DataModule().module,
)

@Suppress("unused")
fun initKoin() = startKoin {
    modules(sharedModules())
}