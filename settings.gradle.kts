pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pokedex"
include(":app:android")
include(":core:model")
include(":core:domain")
include(":core:designsystem")
