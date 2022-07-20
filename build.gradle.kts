// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    /*ext {
        compose_version = Dependencies.Versions.compose_version
    }*/
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (Dependencies.Libs.build_gradle_path)
        classpath (Dependencies.Libs.kotlin_gradle_plugin)
        classpath (Dependencies.Libs.hilt_gradle_plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}