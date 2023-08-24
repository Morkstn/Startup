package com.fiap.startup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.navigation.AppScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen(navController: NavController) {
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        // Text("Tela Principal")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Earnings Today",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier
                        .width(99.dp)
                        .height(20.dp)
                        .align(Alignment.Start) // Alinha no canto esquerdo
                )
                Spacer(modifier = Modifier.height(8.dp)) // Espaço adicionado

                Text(
                    text = "Earnings",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier
                        .width(99.dp)
                        .height(20.dp)
                        .align(Alignment.Start) // Alinha no canto esquerdo
                )

                // Linha personalizada
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black)
                )

                Spacer(modifier = Modifier.height(8.dp)) // Espaço adicionado

                Text(
                    text = "Bonus",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFDFDFD)
                    ),


                    modifier = Modifier
                        .width(99.dp)
                        .height(20.dp)
                        .align(Alignment.Start) // Alinha no canto esquerdo
                )

                // Linha personalizada
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black)
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espaço adicionado

                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Button(
                        onClick = {
                            // Your button click logic here
                        },
                        modifier = Modifier.fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    ) {
                        Text("Claim Bonus")
                    }
                }

            }


        }



        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Gray) // Cor de fundo da nova Box
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "New Content Box", // Texto da nova Box
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)

                )

                Spacer(modifier = Modifier.height(34.dp)) // Espaço adicionado

                Text(
                    text = "Total Earnings",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF)
                    ),

                    modifier = Modifier
                        .width(99.dp)
                        .height(20.dp)
                        .align(Alignment.Start) // Alinha no canto esquerdo
                )
                // Linha personalizada
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.height(8.dp)) // Espaço adicionado

                Text(
                    text = "Average sum per referral",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(300),
                        color = Color(0xFFFFFFFF)
                    ),


                    modifier = Modifier
                        .width(99.dp)
                        .height(20.dp)
                        .align(Alignment.Start) // Alinha no canto esquerdo
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black)
                )



            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
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

            // Novo botão 2
            /*Button(
                    onClick = { *//* Ação para o botão 2 *//* }
            ) {
                Text("Botão 2")
            }*/

        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}
