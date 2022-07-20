class Dependencies {

    object Versions {
        val kotlin_compile_version = "1.5.2"
        val core_ktx_version = "1.8.0"
        val appcompat_version = "1.4.2"
        val android_material_version = "1.6.1"
        val compose_version = "1.1.1"
        val lifecycle_runtime_ktx_version = "2.5.0"
        val activity_compose_version = "1.5.0"

        //testing
        val junit_version = "4.13.2"
        val ext_junit_version = "1.1.3"
        val espresso_version = "3.4.0"
        val mockito_version = "4.0.0"
        val core_test_version = "2.1.0"
        val truth_version = "1.1.3"

        //navigation
        val compose_navigation_version = "2.5.0"

        //Dagger-Hilt
        val hilt_version = "2.40.1"
        val hilt_android_compiler_version = "2.39"
        val hilt_viewmodel_lifecycle_version = "1.0.0-alpha03"
        val hilt_compiler_version = "1.0.0"
        val hilt_navigation_version = "1.0.0"

        //paging
        val paging_ktx_version = "3.1.1"
        val paging_compose_version = "1.0.0-alpha15"

        // Retrofit
        val retrofit_version = "2.9.0"

        //OK Http
        val okhttp_version = "5.0.0-alpha.2"

        // Coroutines
        val coroutine_version = "1.6.1"
        val coroutine_lifecycle_version = "2.5.0"

        // Coil
        val coil_compose_version = "1.4.0"

        // system ui controller
        val system_ui_version = "0.17.0"

        // flow layout
        val flow_layout_version = "0.24.4-alpha"

        // Room
        val room_version = "2.4.2"
        val room_paginting_version = "2.5.0-alpha01"

        val gradle_version = "7.2.1"
        val kotlin_gradle_plugin_version = "1.6.10"

    }

    object Libs {
        val build_gradle_path = "com.android.tools.build:gradle:${Versions.gradle_version}"
        val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_plugin_version}"
        val hilt_gradle_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"

        val core_ktx_lib = "androidx.core:core-ktx:${Versions.core_ktx_version}"
        val appcompact_lib = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
        val android_material_lib =
            "com.google.android.material:material:${Versions.android_material_version}"
        val compose_ui_lib = "androidx.compose.ui:ui:${Versions.compose_version}"
        val compose_material_lib = "androidx.compose.material:material:${Versions.compose_version}"
        val compose_ui_util_lib = "androidx.compose.ui:ui-util:${Versions.compose_version}"
        val compose_ui_tool_preview_lib =
            "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
        val lifecycle_runtime_ktx_lib =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx_version}"
        val activity_compose_lib = "androidx.activity:activity-compose:${Versions.activity_compose_version}"

        //testing
        val junit_lib = "junit:junit:${Versions.junit_version}"
        val ext_junit_lib = "androidx.test.ext:junit:${Versions.ext_junit_version}"
        val espresso_lib = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
        val compose_junit_test_lib = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
        val compose_ui_tool_lib =
            "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
        val mockito_lib = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_version}"
        val coroutine_test_lib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine_version}"
        val core_test_lib = "androidx.arch.core:core-testing:${Versions.core_test_version}"
        val truth_lib = "com.google.truth:truth:${Versions.truth_version}"
        val mockito_core_lib = "org.mockito:mockito-core:${Versions.mockito_version}"

        //navigation
        val compose_navigation_lib = "androidx.navigation:navigation-compose:${Versions.compose_navigation_version}"

        //Dagger-Hilt
        val hilt_lib = "com.google.dagger:hilt-android:${Versions.hilt_version}"
        val hilt_android_compiler_lib = "com.google.dagger:hilt-android-compiler:${Versions.hilt_android_compiler_version}"
        val hilt_viewmodel_lifecycle_lib = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel_lifecycle_version}"
        val hilt_compiler_lib = "androidx.hilt:hilt-compiler:${Versions.hilt_compiler_version}"
        val hilt_navigation_lib = "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_version}"

        //paging
        val paging_ktx_lib = "androidx.paging:paging-runtime-ktx:${Versions.paging_ktx_version}"
        val paging_compose_lib = "androidx.paging:paging-compose:${Versions.paging_compose_version}"

        // Retrofit
        val retrofit_lib = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
        val retrofit_gson_lib = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

        //OK Http
        val okhttp_lib = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"

        // Coroutines
        val coroutines_core_lib = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine_version}"
        val coroutines_android_lib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine_version}"
        val coroutines_play_service_lib = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutine_version}"
        val lifecycle_viewmodel_lib = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.coroutine_lifecycle_version}"

        // Coil
        val coil_compose_lib = "io.coil-kt:coil-compose:${Versions.coil_compose_version}"

        // system ui controller
        val system_ui_lib = "com.google.accompanist:accompanist-systemuicontroller:${Versions.system_ui_version}"

        // flow layout
        val flow_layout_lib = "com.google.accompanist:accompanist-flowlayout:${Versions.flow_layout_version}"

        // Room
        val room_runtime_lib = "androidx.room:room-runtime:${Versions.room_version}"
        val room_compiler_lib = "androidx.room:room-compiler:${Versions.room_version}"
        val room_lib = "androidx.room:room-ktx:${Versions.room_version}"
        val room_paging_lib = "androidx.room:room-paging:${Versions.room_paginting_version}"
    }
}