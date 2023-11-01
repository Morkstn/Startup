package com.fiap.startup.view


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.model.usuarioTeste
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

@Composable
fun LoginScreen(navController: NavHostController, viewModel: usuarioTeste) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Valide os campos (email e senha) conforme necessário
            if (email.isNotBlank() && senha.isNotBlank()) {
                val auth = FirebaseAuth.getInstance()

                auth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Login bem-sucedido, você pode atualizar as informações do usuário
                                // e navegar para a tela principal
                                val user = auth.currentUser

                                // Salve o nome do usuário logado no ViewModel
                                viewModel.nomeUsuarioLogado = user?.displayName ?: ""

                                // Navigate to the main screen
                                navController.navigate("Main")
                            } else {
                                // O login falhou, trate o erro conforme necessário
                                val exception = task.exception
                                if (exception is FirebaseAuthException) {
                                    // Trate erros específicos do Firebase Authentication
                                    // Pode mostrar uma mensagem de erro ao usuário
                                } else {
                                    // Trate outros erros
                                }
                            }
                        }
            }
        }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Código adicional
        Text(
                text = "Ainda não tem uma conta? Cadastre-se",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    // Navegue para a tela de cadastro quando o link for clicado
                    navController.navigate("Create")
                }
        )
    }
}

@RequiresApi(Build.VERSION_CODES. Q)
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    val viewModel = usuarioTeste() // Substitua pelo seu ViewModel
    LoginScreen(navController = navController, viewModel = viewModel)
}

