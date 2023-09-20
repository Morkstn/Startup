package com.fiap.startup.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.database.dao.UsuarioDao
import com.fiap.startup.model.Usuario
import com.fiap.startup.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val usuario = remember { Usuario() }
    val name = usuario.nome
    val saldo = usuario.saldo // Saldo
    var isAlertDialogVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth() // Preencher a largura máxima disponível
                .height(200.dp)
                .background(Color.Red)
            // Definir a altura como 200dp
        ) {
            // Conteúdo da sua tela aqui
            Column(
                modifier = Modifier
                    .fillMaxSize() // Preencher todo o espaço dentro da Box
                    .padding(16.dp) // Adicionar algum preenchimento aos elementos dentro do Column
            ) {
                // Adicione seus elementos ao Column aqui
                Text(text = "$name")

                // ...
            }


        }
        //Criar quatro botões quadrados com o tamanho de 20 dp
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                onClick = { },
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            ) {
                Text("Card 1")
            }

            Card(
                onClick = { },
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            ) {
                Text("Card 2")
            }

            Card(
                onClick = { },
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            ) {
                Text("Card 3")
            }

            Card(
                onClick = { },
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            ) {
                Text("Card 4")
            }
        }

        Text(
            text = "Seus Produtos",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )
        //Spacer(modifier = Modifier.height(16.dp)) // Adicione um espaço entre a Box e o Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp)
        ) {
            // Usar um Row para alinhar "Saldo" e o valor do saldo na mesma linha
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically, // Alinhar verticalmente ao centro
                horizontalArrangement = Arrangement.SpaceBetween // Espaço entre os elementos
            ) {
                Text(
                    text = "Pontos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$saldo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Text(
            text = "Soluções para você",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp)
                .clickable(onClick = {
                    isAlertDialogVisible = true
                })
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
//                Icon(
//                    imageVector = Icons.Default.Heart,
//                    contentDescription = "Icone de coração",
//                    modifier = Modifier.padding(8.dp),
//                )

                Text(
                    text = "Aproveite nossos planos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
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
}


@RequiresApi(Build.VERSION_CODES. Q)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}