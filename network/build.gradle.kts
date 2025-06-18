import java.io.FileInputStream
import java.util.Locale
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.automattic.measure.builds)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.assignment.network"

    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        val properties = Properties()

        all {
            var typeName = name.lowercase(Locale.ROOT)
            if (typeName.lowercase(Locale.ROOT).contentEquals("debug"))
                typeName = "debug"
            val propertiesFile =
                project.rootProject.file("properties/${typeName}.properties")
            properties.load(FileInputStream(propertiesFile))
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false

            buildConfigField("String", "BASE_URL", properties.getProperty("base_url"))
            buildConfigField("String", "API_KEY", properties.getProperty("api_key"))

        }
        getByName("release") {
            isMinifyEnabled = true

            buildConfigField("String", "BASE_URL", properties.getProperty("base_url"))
            buildConfigField("String", "API_KEY", properties.getProperty("api_key"))
        }

    }

    libraryVariants.forEach { variant ->
        variant.sourceSets.forEach {
            it.javaDirectories += files("build/generated/ksp/${variant.name}/kotlin")
        }
    }
    composeCompiler {
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    ksp {
        arg("KOIN_CONFIG_CHECK", "true")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core"))

    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose.android)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.coil)
    implementation(libs.material3)
    implementation(libs.navigation)
    implementation(libs.junit)
    implementation(libs.core.ktx)
    androidTestImplementation(libs.junit)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.ui.tooling)
    androidTestImplementation(libs.ui.test.manifest)
    implementation(libs.coroutines.core)
    implementation(libs.kotlin)
    implementation(libs.loggingInterceptor)
    implementation(libs.gson)

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.gson)
}