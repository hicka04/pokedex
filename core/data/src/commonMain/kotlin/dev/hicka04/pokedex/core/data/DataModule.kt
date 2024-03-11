package dev.hicka04.pokedex.core.data

import dev.hicka04.pokedex.core.network.NetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [NetworkModule::class])
@ComponentScan
class DataModule