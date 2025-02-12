plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs.kotlin")
//    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.shop.user"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.shop.user"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // UI Libraries
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.circleimageview)
    implementation(libs.dotsindicator)
    implementation(libs.github.progressbutton)

    // Room libraries
    implementation(libs.androidx.room.runtime)
    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    // See Add the KSP plugin to your project
//    ksp(libs.androidx.room.compiler)
    // If this project only uses Java source, use the Java annotationProcessor
    // No additional plugins are necessary
//    val room_version = "2.6.1"
    annotationProcessor(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.androidx.room.guava)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    // Navigation Libraries
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Lifecycle and ViewModel Libraries
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.extensions)

    // Networking Libraries
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Logging
    implementation(libs.timber)

    // Image Loading Libraries
    implementation(libs.glide)

    // Firebase Libraries
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.firebase.firestore)

    // Android Core Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity)
}
kapt {
    correctErrorTypes = true
}