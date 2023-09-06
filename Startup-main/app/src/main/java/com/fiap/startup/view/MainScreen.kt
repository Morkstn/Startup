package com.fiap.startup.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.model.Usuario
import com.fiap.startup.navigation.AppScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Conteúdo do saldo dentro da Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val usuario = remember { Usuario() }
            val saldo = usuario.saldo
            // Column para organizar o saldo e "Balance" verticalmente
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Saldo
                Text(text = "${saldo}")
                // Espaçador entre o saldo e "Balance"
                Spacer(modifier = Modifier.height(8.dp))
                // Texto "Balance"
                Text(
                    text = "Balance Total",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
        // Espaçador
        Spacer(modifier = Modifier.height(16.dp))

        // Botões fora da Box, no final da tela
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botão para navegar para a rota ExplorerScreen
            Button(
                onClick = { navController.navigate(AppScreen.ExplorerScreen.route) }
            ) {
                Text("Explorer")
            }
            // Novo botão 1
            Button(
                onClick = { navController.navigate(AppScreen.ProfileScreen.route) }
            ) {
                Text("Profile")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES. Q)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}