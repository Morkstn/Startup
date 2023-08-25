package com.fiap.startup.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.ExplorerScreen
import com.fiap.startup.LoginScreen
import com.fiap.startup.MainScreen
import com.fiap.startup.PasswordRecoveryScreen
import com.fiap.startup.ProfileScreen
import com.fiap.startup.SingUpScreen
import com.fiap.startup.SplashScreen

@RequiresApi(Build.VERSION_CODES.Q)
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
            MainScreen(navController = navController)
        }

        composable(AppScreen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }

        composable(AppScreen.ExplorerScreen.route){
            ExplorerScreen(navController = navController)
        }

        // Configurando a barra de navegação para a tela principal

        composable(AppScreen.PasswordRecoveryScreen.route){
            PasswordRecoveryScreen(navController = navController)
        }

    }
}

