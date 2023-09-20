package com.fiap.startup.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.R
import com.fiap.startup.model.Usuario
import com.fiap.startup.navigation.AppScreen

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val usuario = remember { Usuario() }
    val saldo = usuario.saldo
    var isAlertDialogVisible by remember { mutableStateOf(false) }
    val name = usuario.nome

    Column(
        modifier = Modifier.fillMaxSize().fillMaxHeight().verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp).clip(shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)).background(
                Brush.horizontalGradient(
                    colors = listOf(Color.Red, Color.Blue)
                )

            )

        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Text(text = "$name",
                        color = Color.White)
                Spacer(modifier = Modifier.height(70.dp)) // Adiciona espaço entre os textos
                Text(
                    text = "O meio ambiente é a nossa casa. Devemos cuidar dele para as futuras gerações.", // Adiciona uma frase sobre o meio ambiente
                    fontSize = 15.sp, // Ajusta o tamanho da fonte
                    fontWeight = FontWeight.Bold, // Define o peso da fonte como negrito
                    color = Color.White,
                    textAlign = TextAlign.Left,

                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                onClick = { navController.navigate(AppScreen.SettingsScreen.route) },
                modifier = Modifier.width(80.dp).height(80.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(40.dp), // Tamanho do espaço do ícone
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = null) // Adiciona um ícone de configurações
                    }
                    Text("Settings", textAlign = TextAlign.Center)
                }
            }

            Card(
                onClick = {  isAlertDialogVisible = true  },
                modifier = Modifier.width(80.dp).height(80.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(40.dp), // Tamanho do espaço do ícone
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null) // Adiciona um ícone
                    }
                    Text("Claim", textAlign = TextAlign.Center)
                }
            }


            Card(
                onClick = { navController.navigate(AppScreen.ProfileScreen.route) },
                modifier = Modifier.width(80.dp).height(80.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(40.dp), // Tamanho do espaço do ícone
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.AccountBox, contentDescription = null) // Adiciona um ícone
                    }
                    Text("Profile", textAlign = TextAlign.Center)
                }
            }

            Card(
                onClick = { navController.navigate(AppScreen.ExplorerScreen.route) },
                modifier = Modifier.width(80.dp).height(80.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(40.dp), // Tamanho do espaço do ícone
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.LocationOn, contentDescription = null) // Adiciona um ícone de exploração
                    }
                    Text("Explorer", textAlign = TextAlign.Center)
                }
            }
        }

        Text(
            text = "Seus Produtos",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth().height(150.dp).padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    modifier = Modifier.size(30.dp).align(Alignment.Start),
                    painter = painterResource(id = R.drawable.logo_mjv),
                    contentDescription = "Logo MJV",
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Saldo MJV",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "$saldo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Divider(
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isAlertDialogVisible =
                                    true
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Acessar saldo",
                            )
                            Icon(Icons.Default.ArrowForward, contentDescription = null)
                        }
                    }

                }
            }
        }

        Text(
            text = "Soluções para você",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth().height(100.dp).padding(16.dp).clickable(onClick =
            {
                isAlertDialogVisible =
                    true }),
        ) {
            Row(
                modifier =
                Modifier.fillMaxWidth(),
                verticalAlignment =
                Alignment.CenterVertically,
            ) {

// Icon(imageVector =
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Icon de estrela",
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "Aproveite nossos planos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

            }
        }

        Text(
            text = "Descubra mais",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 8.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    onClick = { isAlertDialogVisible =
                        true},
                    modifier = Modifier.width(175.dp).height(175.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                        Text("Indicar Amigos", textAlign = TextAlign.Center)
                    }
                }
            }

            item {
                Card(
                    onClick = { isAlertDialogVisible =
                        true },
                    modifier = Modifier.width(175.dp).height(175.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                        }
                        Text("Em que é baseado meus pontos? ", textAlign = TextAlign.Center)
                    }
                }

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

@RequiresApi(Build.VERSION_CODES. Q)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}
