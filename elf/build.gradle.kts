plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.dokka")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    kotlinOptions {
        jvmTarget = Config.javaVersion.toString()
        freeCompilerArgs = (freeCompilerArgs + Config.freeCompilerArgs).distinct()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha03"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:1.7.0")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.6.10")


    val hiltVersion = "2.41"
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt ("com.google.dagger:hilt-compiler:$hiltVersion")

    val compose_version = "1.2.0-alpha07"
    implementation ("androidx.compose.runtime:runtime:$compose_version")
    implementation ("androidx.activity:activity-compose:1.4.0")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

publishing {
    publications {
        repositories {
            maven {
                val releasesUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                val snapshotsUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsUrl else releasesUrl
                credentials {
                    username = project.properties["ossrhUsername"].toString()
                    password = project.properties["ossrhPassword"].toString()
                }
            }
        }
    }
}