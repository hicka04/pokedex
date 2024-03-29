import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.jvm.get()
            }
        }
    }
    
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "network"
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(project(":core:model"))
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.bundles.ktor.multiplatform)
                implementation(libs.bundles.koin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.test)
                implementation(libs.io.ktor.client.mock)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.bundles.ktor.android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.bundles.ktor.ios)
            }
        }
    }
}

android {
    namespace = "dev.hicka04.pokedex.core.network"
    compileSdk = libs.versions.sdk.compile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.io.insert.koin.ksp.compiler)
}