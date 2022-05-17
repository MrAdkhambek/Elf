import org.gradle.api.JavaVersion


object Config {
    const val compileSdkVersion = 31
    const val buildToolsVersion = "31"

    const val applicationId = "me.adkhambek.elf"
    const val minSdkVersion = 21
    const val targetSdkVersion = 31
    const val versionCode = 2
    const val versionName = "1.0"
    const val multiDexEnabled = true
    const val consumerProguardFiles = "consumer-rules.pro"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val javaVersion = JavaVersion.VERSION_11

    val freeCompilerArgs = listOf(
        "-Xuse-experimental=kotlinx.serialization.ExperimentalSerializationApi",
        "-Xuse-experimental=kotlinx.serialization.InternalSerializationApi",
        "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",

        "-Xuse-experimental=androidx.compose.material3.ExperimentalMaterial3Api",
        "-Xuse-experimental=androidx.compose.animation.ExperimentalAnimationApi",

        "-Xopt-in=kotlin.contracts.ExperimentalContracts",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xjvm-default=all",
    )
}