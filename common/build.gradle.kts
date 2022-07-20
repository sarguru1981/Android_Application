plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles ("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation (Dependencies.Libs.core_ktx_lib)
    implementation (Dependencies.Libs.appcompact_lib)
    implementation (Dependencies.Libs.android_material_lib)
    testImplementation (Dependencies.Libs.junit_lib)
    androidTestImplementation (Dependencies.Libs.ext_junit_lib)
    androidTestImplementation (Dependencies.Libs.espresso_lib)

    //navigation
    implementation (Dependencies.Libs.compose_navigation_lib)

    //Dagger - Hilt
    implementation (Dependencies.Libs.hilt_lib)

    // Dagger - Hilt
    kapt (Dependencies.Libs.hilt_android_compiler_lib)
    implementation (Dependencies.Libs.hilt_viewmodel_lifecycle_lib)
    kapt (Dependencies.Libs.hilt_compiler_lib)
    implementation (Dependencies.Libs.hilt_navigation_lib)

    // Retrofit
    implementation (Dependencies.Libs.retrofit_lib)
    implementation (Dependencies.Libs.retrofit_gson_lib)
    implementation (Dependencies.Libs.okhttp_lib)
    kapt (Dependencies.Libs.hilt_compiler_lib)

    // Coroutines
    implementation (Dependencies.Libs.coroutines_core_lib)
    implementation (Dependencies.Libs.coroutines_android_lib)
    implementation (Dependencies.Libs.coroutines_play_service_lib)

    // Coroutine Lifecycle Scopes
    implementation (Dependencies.Libs.lifecycle_viewmodel_lib)
    implementation (Dependencies.Libs.lifecycle_runtime_ktx_lib)

    // Coil
    implementation (Dependencies.Libs.coil_compose_lib)

    // system ui controller
    implementation (Dependencies.Libs.system_ui_lib)

    // flow layout
    implementation (Dependencies.Libs.flow_layout_lib)
}