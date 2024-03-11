package dev.hicka04.pokedex.android

import android.app.Application
import dev.hicka04.pokedex.core.data.DataModule
import dev.hicka04.pokedex.core.domain.di.domainModule
import dev.hicka04.pokedex.feature.pokemon_list.di.featurePokemonListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(
                listOf(
                    domainModule,
                    DataModule().module,
                    featurePokemonListModule,
                )
            )
        }
    }
}