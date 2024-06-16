import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.co.touchlab.skie)
    alias(libs.plugins.com.google.devtools.ksp)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

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
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                api(project(":core:model"))
                api(project(":core:domain"))

                implementation(project(":core:data"))
                implementation(libs.bundles.koin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.io.insert.koin.ksp.compiler)
}