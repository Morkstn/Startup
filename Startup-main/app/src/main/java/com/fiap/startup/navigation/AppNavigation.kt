package com.fiap.startup.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.database.repository.UsuarioRepository
import com.fiap.startup.model.usuarioTeste
import com.fiap.startup.view.DescriptionScreen
import com.fiap.startup.view.ExplorerScreen
import com.fiap.startup.view.LoginScreen
import com.fiap.startup.view.MainScreen
import com.fiap.startup.view.PasswordRecoveryScreen
import com.fiap.startup.view.ProfileScreen
import com.fiap.startup.view.RewardsScreen
import com.fiap.startup.view.SettingsScreen
import com.fiap.startup.view.SingUpScreen
import com.fiap.startup.view.SplashScreen
import com.fiap.startup.view.StatementScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current // Obtenha o contexto local
    val usuarioRepository = UsuarioRepository(context) // Crie uma instância do UsuarioRepository
    val viewModel: usuarioTeste = viewModel()



    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        /// login para tela principal

        composable(AppScreen.SingUpScreen.route){
            SingUpScreen(navController,  viewModel)
        }

        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController, viewModel )
        }

        composable(AppScreen.MainScreen.route) {
            MainScreen(navController,  viewModel)
        }

        composable(AppScreen.RewardsScreen.route) {
            RewardsScreen(navController = navController)
        }
        composable(AppScreen.ProfileScreen.route){
            ProfileScreen(navController, viewModel)
        }
        composable(AppScreen.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }

        composable(AppScreen.StatementScreen.route){
            StatementScreen(navController = navController)
        }

        composable(AppScreen.ExplorerScreen.route){
            ExplorerScreen(navController = navController)
        }

        // Configurando a barra de navegação para a tela principal

        composable(AppScreen.PasswordRecoveryScreen.route){
            PasswordRecoveryScreen(navController = navController)
        }

        composable(AppScreen.DescriptionScreen.route){
            DescriptionScreen(navController = navController)
        }

    }
}


