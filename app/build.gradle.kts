plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
//    id ("dagger.hilt.android.plugin")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}

apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.dicoding.kickden"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.kickden"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
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
    dynamicFeatures += (":favorite")
}

dependencies {
    implementation (project(":core"))
//    implementation (project(":myfeaturemodule"))

    implementation (fileTree("libs") {include("*.jar")} )
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
}