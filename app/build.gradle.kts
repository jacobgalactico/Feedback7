plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.feedback5"
    compileSdk = 35 // Actualiza a 35

    defaultConfig {
        applicationId = "com.example.feedback5"
        minSdk = 24
        targetSdk = 35 // También puedes actualizar el targetSdk a 35 para nuevas optimizaciones de Android
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    implementation("com.google.android.gms:play-services-location:21.0.1") // Ubicación
    implementation("com.google.android.gms:play-services-maps:18.2.0")    // Google Maps
    implementation("com.google.maps.android:maps-compose:4.2.0")          // Maps en Compose
    implementation("com.google.accompanist:accompanist-permissions:0.31.0-alpha") // Permisos
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
    implementation("com.google.accompanist:accompanist-permissions:0.31.0-alpha")





    implementation(libs.datastore.preferences)
    implementation(libs.datastore.core)
    implementation(libs.serialization.json)
    implementation(libs.serialization.core)
    implementation(libs.serialization.protobuf)
    implementation(libs.serialization.cbor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}