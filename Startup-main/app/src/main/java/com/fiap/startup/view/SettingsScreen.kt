package com.fiap.startup.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.navigation.AppScreen
import com.fiap.startup.navigation.BackButton


@Composable
fun SettingsScreen(navController: NavController){
    var isAlertDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(navController = navController)
            Spacer(modifier = Modifier.width(5.dp))
            Text("Settings")
        }

        // Card retangular com ícone de ajuda como botão
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(14.dp)
                .clickable(onClick = {
                    isAlertDialogVisible = true
                }),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Icon de ajuda",
                    modifier = Modifier
                        .size(25.dp, 50.dp) // Altere o tamanho do ícone conforme desejado

                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaço entre o ícone e o texto
                Text(
                    text = "Ajuda",
                    fontSize = 16.sp,

                    )
            }
        }

        // Card retangular com ícone de ajuda como botão
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(14.dp)
                .clickable(onClick = {
                    isAlertDialogVisible = true
                }),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Icon de termos",
                    modifier = Modifier
                        .size(25.dp, 50.dp) // Altere o tamanho do ícone conforme desejado
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaço entre o ícone e o texto
                Text(
                    text = "Termos de uso",
                    fontSize = 16.sp,

                    )
            }
        }

        // Card retangular com ícone de ajuda como botão
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(14.dp)
                .clickable(onClick = {
                    navController.navigate(AppScreen.SingUpScreen.route)
                }),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = "Icon de sair",
                    modifier = Modifier
                        .size(25.dp, 50.dp) // Altere o tamanho do ícone conforme desejado
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaço entre o ícone e o texto
                Text(
                    text = "Sair",
                    fontSize = 16.sp,

                    )
            }
        }


    }

    if (isAlertDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                isAlertDialogVisible = false
            },
            title = { Text(text = "Aviso") },
            text = { Text(text = "A funcionalidade estará disponível em breve") },
            confirmButton = {
                Button(
                    onClick = {
                        isAlertDialogVisible = false
                    }
                ) {
                    Text("Ok")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview(){
    val navController = rememberNavController()
    SettingsScreen(navController)
}