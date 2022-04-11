# navigation-compose

[![Maven Central](https://maven-badges-generator.herokuapp.com/maven-central/ro.dragossusi/messagedata/badge.svg)](https://maven-badges-generator.herokuapp.com/maven-central/ro.dragossusi/navigation)

Gradle:

```kotlin
dependencies {
    implementation("ro.dragossusi:navigation:<version>")
}
```

### Features

- Route navigation
- Key-Value arguments
- Backstack
- ViewModel

### Usage

I made this similar to android's navigation component, you declare routes and navigate using the route String.

There is also a sample app in `sample` directory.

Create a NavController using

```kotlin
val navController = rememberNavController()
```

Create the graph and declare the composable methods like this:

```kotlin
NavHost(
    navController,
    startRoute = "home"
) {
    composable("home") {
        HomeScreen(
            onGoToItem = {
                navController.navigate("item")
            },
            onGoToHelp = {
                navController.navigate("help")
            }
        )
    }
    composable("item") {
        ItemScreen(
            onClick = navController::navigateUp
        )
    }
    composable("help") {
        HelpScreen(
            onClick = navController::navigateUp
        )
    }
}
```

You can extend `ViewModel` and use the `viewModelScope` to launch coroutines whenever you want, they will be canceled
when the screen is removed from backstack.

To obtain a `ViewModel` instance use the `rememberViewModel` method.

```kotlin
val viewModel: ItemViewModel = rememberViewModel { arguments ->
    //here you create your ViewModel however you want, it will be reused
    ItemViewModel()
}
```