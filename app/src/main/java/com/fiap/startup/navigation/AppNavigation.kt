package com.fiap.startup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.ExplorerScreen
import com.fiap.startup.LoginScreen
import com.fiap.startup.MainScreen
import com.fiap.startup.ProfileScreen
import com.fiap.startup.SingUpScreen
import com.fiap.startup.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        /// login para tela principal

        composable(AppScreen.SingUpScreen.route){
            SingUpScreen(navController)
        }

        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(AppScreen.MainScreen.route) {
            MainScreen()
        }

        composable(AppScreen.ProfileScreen.route){
            ProfileScreen()
        }

        composable(AppScreen.ExplorerScreen.route){
            ExplorerScreen()
        }

        // Configurando a barra de navegação para a tela principal



    }
}