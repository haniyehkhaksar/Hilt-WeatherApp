// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(GradlePlugins.ANDROID_GRADLE)
        classpath(kotlin(GradlePlugins.KOTLIN_GRADLE_PLUGIN_MODULE, version = CoreVersion.KOTLIN))
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.37")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
