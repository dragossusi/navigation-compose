plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "ro.dragossusi"
version = Versions.app


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(compose.desktop.currentOs)
    implementation(project(":navigation"))
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:${Versions.coroutines}")
}