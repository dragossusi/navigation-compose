// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.android}")
    }
}

plugins {
    kotlin("multiplatform") version Versions.kotlin apply false
    id("org.jetbrains.compose") version Versions.compose apply false
    `maven-publish`
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}