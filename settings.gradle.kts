rootProject.name = "navigation-compose"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":navigation")
include(":navigation-kodein")
include(":sample")
