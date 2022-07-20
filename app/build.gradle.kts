plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.poc.featureapp"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":presentation"))

    implementation (Dependencies.Libs.core_ktx_lib)
    implementation (Dependencies.Libs.appcompact_lib)
    implementation (Dependencies.Libs.android_material_lib)
    implementation (Dependencies.Libs.compose_ui_lib)
    implementation (Dependencies.Libs.compose_material_lib)
    implementation (Dependencies.Libs.compose_ui_tool_preview_lib)
    implementation (Dependencies.Libs.lifecycle_runtime_ktx_lib)
    implementation (Dependencies.Libs.activity_compose_lib)
    testImplementation (Dependencies.Libs.junit_lib)
    androidTestImplementation (Dependencies.Libs.ext_junit_lib)
    androidTestImplementation (Dependencies.Libs.espresso_lib)
    androidTestImplementation (Dependencies.Libs.compose_junit_test_lib)
    debugImplementation (Dependencies.Libs.compose_ui_tool_lib)

    //navigation
    implementation (Dependencies.Libs.compose_navigation_lib)

    //Dagger - Hilt
    implementation (Dependencies.Libs.hilt_lib)

    // Dagger - Hilt
    kapt (Dependencies.Libs.hilt_android_compiler_lib)
    implementation (Dependencies.Libs.hilt_viewmodel_lifecycle_lib)
    kapt (Dependencies.Libs.hilt_compiler_lib)
    implementation (Dependencies.Libs.hilt_navigation_lib)

    // paging 3
    implementation (Dependencies.Libs.paging_ktx_lib)
    implementation (Dependencies.Libs.paging_compose_lib)

    // Retrofit
    implementation (Dependencies.Libs.retrofit_lib)
    implementation (Dependencies.Libs.retrofit_gson_lib)
    //May need okkhttp also
    implementation (Dependencies.Libs.okhttp_lib)

    // Coroutines
    implementation (Dependencies.Libs.coroutines_core_lib)
    implementation (Dependencies.Libs.coroutines_android_lib)
    implementation (Dependencies.Libs.coroutines_play_service_lib)

    // Coroutine Lifecycle Scopes
    implementation (Dependencies.Libs.lifecycle_viewmodel_lib)

    // Coil
    implementation (Dependencies.Libs.coil_compose_lib)

    // system ui controller
    implementation (Dependencies.Libs.system_ui_lib)

    // flow layout
    implementation (Dependencies.Libs.flow_layout_lib)
}