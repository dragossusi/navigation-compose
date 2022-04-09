import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "ro.dragossusi"
version = Versions.app


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(compose.desktop.currentOs)
    @OptIn(ExperimentalComposeLibrary::class)
    implementation(compose.material3)
    implementation(project(":navigation"))
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:${Versions.coroutines}")
}