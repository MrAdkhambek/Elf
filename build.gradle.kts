buildscript {
    val kotlinVersion = "1.6.10"
    extra["kotlin_version"] = kotlinVersion

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.dokka", "dokka-gradle-plugin", kotlinVersion)
        classpath("org.jetbrains.kotlin", "kotlin-gradle-plugin", kotlinVersion)
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.19.0")
    }
}

allprojects {
    group = "me.adkhambek.elf"
    version = "0.0.1"

    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
            publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.S01)
            signAllPublications()
        }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}