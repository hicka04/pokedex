import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.com.android.library)
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
        it.binaries{
            framework {
                baseName = "shared"
                binaryOption("bundleId", "dev.hicka04.pokedex.shared")
                binaryOption("bundleVersion", version.toString())
                binaryOption("bundleShortVersionString", version.toString())
                xcf.add(this)

                export(project(":core:model"))
                export(project(":core:domain"))
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core:model"))
                api(project(":core:domain"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "dev.hicka04.pokedex.shared"
    compileSdk = libs.versions.sdk.compile.get().toInt()
    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
    }
}