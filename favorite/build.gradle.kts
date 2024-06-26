plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
//    id ("com.google.dagger.hilt.android")
}
apply (from = "../shared_dependencies.gradle")


android {
    namespace = "com.dicoding.kikcden.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(":core"))
    implementation(project(":app"))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")

}