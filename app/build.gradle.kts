plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        multiDexEnabled = Config.multiDexEnabled
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = false
            isMinifyEnabled = false
        }
        getByName("debug") {
            isShrinkResources = false
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha07"
    }

    kotlinOptions {
        jvmTarget = Config.javaVersion.toString()
        freeCompilerArgs = (freeCompilerArgs + Config.freeCompilerArgs).distinct()
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {
    implementation (project(":elf"))

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.6.0")

    implementation ("androidx.activity:activity-compose:1.4.0")

    val coroutines_version = "1.6.0"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val hiltVersion = "2.41"
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt ("com.google.dagger:hilt-compiler:$hiltVersion")

    val compose_version = "1.2.0-alpha07"
    implementation ("androidx.compose.ui:ui-test:$compose_version")
    implementation ("androidx.compose.ui:ui-text:$compose_version")
    implementation ("androidx.compose.ui:ui-util:$compose_version")

    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    runtimeOnly ("androidx.compose.ui:ui-tooling-preview:$compose_version")

    implementation ("androidx.compose.runtime:runtime:$compose_version")
    implementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
    implementation ("androidx.compose.material:material:$compose_version")
    implementation ("androidx.compose.ui:ui-viewbinding:$compose_version")
    implementation ("androidx.compose.foundation:foundation:$compose_version")
    implementation ("androidx.compose.foundation:foundation-layout:$compose_version")
    implementation ("androidx.compose.runtime:runtime-livedata:$compose_version")
    implementation ("androidx.compose.material:material-icons-extended:$compose_version")

    val mviVersion = "4.3.2"
    implementation("org.orbit-mvi:orbit-core:$mviVersion")
    implementation("org.orbit-mvi:orbit-viewmodel:$mviVersion")

    debugImplementation ("com.squareup.leakcanary:leakcanary-android:2.8.1")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}