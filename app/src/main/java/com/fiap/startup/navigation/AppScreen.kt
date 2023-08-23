package com.fiap.startup.navigation

sealed class AppScreen(val route: String) {


    object SplashScreen: AppScreen("splash_screen")

    object LoginScreen: AppScreen("login_screen")

    object SingUpScreen: AppScreen("singUp_screen")

    object MainScreen: AppScreen("main_screen")

    object ExplorerScreen: AppScreen("explorer_screen")

    object ProfileScreen: AppScreen("profile_screen")

}
