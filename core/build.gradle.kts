plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
//    id ("dagger.hilt.android.plugin")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}
apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.dicoding.kickden.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree("libs") {include("*.jar")} )
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")
}