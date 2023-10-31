package com.fiap.startup.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fiap.startup.database.User
import com.fiap.startup.model.usuarioTeste
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase

@Composable
fun SingUpScreen(navController: NavHostController, viewModel: usuarioTeste) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }


    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material.Text("Cadastro", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { androidx.compose.material.Text("Nome") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = email,
                onValueChange = { email = it },
                label = { androidx.compose.material.Text("Email") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { androidx.compose.material.Text("Senha") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { androidx.compose.material.Text("CPF") },
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        androidx.compose.material.Button(onClick = {
            // Valide os campos (nome, email, senha, cpf) conforme necessário
            if (nome.isNotBlank() && email.isNotBlank() && senha.isNotBlank() && cpf.isNotBlank()) {
                val auth = FirebaseAuth.getInstance()

                auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Cadastro bem-sucedido, você pode agora atualizar as informações do usuário
                                // e navegar para a tela principal
                                val user = auth.currentUser
                                if (user != null) {
                                    val firebaseDatabase = FirebaseDatabase.getInstance()
                                    val databaseReference = firebaseDatabase.reference.child("user")

                                    val userData = User(nome, cpf) // Crie uma classe User com campos nome e cpf

                                    // Salve os dados do usuário no Firebase Realtime Database
                                    databaseReference.child(user.uid).setValue(userData)

                                    // Salve o nome do usuário no ViewModel
                                    viewModel.nomeUsuario = nome
                                    viewModel.emailUsuario = email
                                    viewModel.senhaUsuario = senha
                                    viewModel.cpf = cpf

                                    // Set the logged-in user's name in the ViewModel
                                    viewModel.nomeUsuarioLogado = nome


                                    // Navigate to the main screen
                                    navController.navigate("Main")
                                }
                            } else {
                                // O cadastro falhou, trate o erro conforme necessário
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
            androidx.compose.material.Text("Cadastrar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Código adicional
        Text(
                text = "Já tem uma conta? Faça login",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    // Navegue para a tela de login quando o link for clicado
                    navController.navigate("Login")
                }
        )
    }
}