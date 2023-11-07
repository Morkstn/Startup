package com.fiap.startup.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.Assets.email
import com.fiap.startup.R
import com.fiap.startup.navigation.AppScreen
import com.fiap.startup.navigation.BackButton
import com.fiap.startup.navigation.URLLauncher
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SettingsScreen(navController: NavController){
    var isAlertDialogVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Lida com o resultado da ação (por exemplo, pode verificar se a URL foi aberta com sucesso)
    }

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
//                    isAlertDialogVisible = true
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    context.startActivity(intent)
                }),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                val customIcon = painterResource(id = R.drawable.info) // Substitua "ic_custom" pelo nome do seu ícone personalizado
                Image(painter = customIcon, contentDescription = null) // Adiciona um ícone de configurações
//                Icon(
//                    imageVector = Icons.Filled.Info,
//                    contentDescription = "Icon de ajuda",
//                    modifier = Modifier
//                        .size(25.dp, 50.dp) // Altere o tamanho do ícone conforme desejado
//
//                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaço entre o ícone e o texto
                Text(
                    text = "Ajuda",
                    fontSize = 16.sp,

                    )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(14.dp)
                .clickable(onClick = {
                    val url = "https://drive.google.com/file/d/1oiepXK9ibubMtOQXJwmEhbNWF231F5_d/view" // Substitua pela sua URL externa
                    URLLauncher.launchURL(url, context, launcher)
                }),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                val customIcon = painterResource(id = R.drawable.home) // Substitua "ic_custom" pelo nome do seu ícone personalizado
                Image(painter = customIcon, contentDescription = null) // Adiciona um ícone de configurações
//                Icon(
//                    imageVector = Icons.Filled.home,
//                    contentDescription = "Icon de termos",
//                    modifier = Modifier
//                        .size(25.dp, 50.dp) // Altere o tamanho do ícone conforme desejado
//                )
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
                    val auth = FirebaseAuth.getInstance()
                    auth.signOut() // Isso irá deslogar o usuário
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
                val customIcon = painterResource(id = R.drawable.exit) // Substitua "ic_custom" pelo nome do seu ícone personalizado
                Image(painter = customIcon, contentDescription = null) // Adiciona um ícone de configurações
//                Icon(
//                    imageVector = Icons.Filled.ExitToApp,
//                    contentDescription = "Icon de sair",
//                    modifier = Modifier
//                        .size(25.dp, 50.dp) // Altere o tamanho do ícone conforme desejado
//                )
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

