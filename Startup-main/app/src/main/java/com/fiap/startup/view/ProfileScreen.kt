package com.fiap.startup.view

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.fiap.startup.R
import com.fiap.startup.model.Usuario
import com.fiap.startup.model.getNome
import com.fiap.startup.model.usuarioTeste
import com.fiap.startup.navigation.BackButton
import androidx.lifecycle.ViewModel



@Composable
fun ProfileScreen(navController: NavController, ViewModel: usuarioTeste) {
    val viewModel = usuarioTeste()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(0.dp),
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
            Text("Profile")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Exibir o avatar do usuário aqui
            Image(
                painter = painterResource(id = R.drawable.avatar_user), // Substitua pelo recurso de imagem do avatar
                contentDescription = null, // Substitua pela descrição adequada
                modifier = Modifier.size(180.dp) // Defina o tamanho do avatar
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                // Exiba o nome do usuário aqui
                " ${viewModel.nomeUsuarioLogado}",// Exibe o nome do usuário do ViewModel
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 18.sp
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val navController = rememberNavController()
    val viewModel = usuarioTeste()
    ProfileScreen(navController, viewModel)
}