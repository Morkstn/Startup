package com.fiap.startup.navigation


sealed class AppScreen(val route: String,  val title: String) {


    object SplashScreen: AppScreen("Splash", "splash_screen")

    object LoginScreen: AppScreen("Login","login_screen")

    object  PasswordRecoveryScreen: AppScreen("Reset", "reset_account")

    //object  ChooseNewPassword: AppScreen ("NewPassword", "reset_password")
    object SingUpScreen: AppScreen("Create","singUp_screen")


    object MainScreen: AppScreen("Main","main_screen")

    object SettingsScreen: AppScreen("Setings", "settings_screen")

    object ExplorerScreen: AppScreen("Explorer","explorer_screen")

    object ProfileScreen: AppScreen("Profile","profile_screen")



}
