package com.fiap.startup.view

import androidx.compose.material.icons.Icons.Filled
import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.model.usuarioTeste
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: usuarioTeste) {
    var email by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    // Cria uma variável para controlar a visibilidade da senha
    var isPasswordVisible by remember { mutableStateOf(false) }
    var senha by remember { mutableStateOf("") }
    // Variável para controlar a visibilidade da mensagem de erro
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().focusRequester(emailFocusRequester), // Define o foco
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next // Define a ação do teclado virtual
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth().fillMaxWidth()
                    .focusRequester(passwordFocusRequester), // Define o foco
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done, // Define a ação do teclado virtual
                        keyboardType = KeyboardType.Password,
            ),keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (email.isNotBlank() && senha.isNotBlank()) {
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Login bem-sucedido
                            val user = auth.currentUser
                            viewModel.nomeUsuarioLogado = user?.displayName ?: ""
                            navController.navigate("Main")
                        } else {
                            // O login falhou, trate o erro
                            val exception = task.exception
                            if (exception is FirebaseAuthException) {
                                when (exception.errorCode) {
                                    "ERROR_INVALID_EMAIL" -> {
                                        // Email inválido
                                        // Trate o erro, por exemplo, mostrando uma mensagem de erro

                                        showError = true
                                        errorMessage = "Email inválido"

                                    }
                                    "ERROR_WRONG_PASSWORD" -> {
                                        // Senha incorreta
                                        // Trate o erro, por exemplo, mostrando uma mensagem de erro
                                        showError = true
                                        errorMessage = "Senha incorreta"

                                    }
                                    "ERROR_USER_NOT_FOUND" -> {
                                        // Usuário não cadastrado com esse email
                                        // Trate o erro, por exemplo, mostrando uma mensagem de erro
                                        showError = true
                                        errorMessage = "Usuário não cadastrado com esse email"
                                    }
                                    else -> {
                                        // Outro erro específico do Firebase Authentication
                                        // Trate o erro apropriadamente
                                    }
                                }
                            } else {
                                // Trate outros erros
                            }
                        }
                    }
            }
        },
            modifier = Modifier
                .width(200.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(15.dp)),
            )
        {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Código adicional
        Text(
                text = "Forgot password?",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    // Navegue para a tela de cadastro quando o link for clicado
                    navController.navigate("Reset")
                }
        )
        // Mostra a mensagem de erro se showError for true
        if (showError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
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

