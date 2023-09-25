pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pokedex"
include(":app:android")
include(":shared")
include(":core:model")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":core:designsystem")
include(":feature:pokemon_list")
