plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.org.jetbrains.kotlin.serializable)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }


    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)

            implementation(libs.koin.android)
            implementation(libs.koin.compose)

            // common google
            implementation(compose.preview)
            implementation(libs.activity.compose)
           // implementation(libs.paging.common.android)
           // implementation(libs.paging.compose)
            implementation(libs.lifecycle.viewmodel.ktx)
            //implementation(libs.lifecycle.viewmodel.savedstate.android) // alpha

        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.stately.common)
            implementation(libs.stately.isolate)
            implementation(libs.stately.iso.collection)
            implementation(libs.sqldelight.native.driver)
        }

        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)
                implementation(libs.ktor.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.content)
                implementation(libs.sqldelight.paging)
                implementation(libs.sqldelight.runtime)
                implementation(libs.coil)
                implementation(libs.coil.network.ktor)

                // common google
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.paging.common)
                implementation(libs.paging.compose.common)
                implementation(libs.lifecycle.common)
                implementation(libs.lifecycle.runtime)
                implementation(libs.lifecycle.viewmodel)
                //implementation(libs.lifecycle.viewmodel.savedstate) // alpha

                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "com.amarchaud.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin.sourceSets.configureEach {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
}


sqldelight {
    databases {
        register("PaginationDemoDatabase") { // The name of the database
            packageName.set("com.amarchaud.database") // Package name used for the database class.
        }
    }
}
