package dev.hicka04.pokedex.shared

import dev.hicka04.pokedex.core.data.DataModule
import dev.hicka04.pokedex.core.domain.DomainModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

@Suppress("unused")
fun initKoin() = startKoin {
    modules(
        DomainModule().module,
        DataModule().module,
    )
}