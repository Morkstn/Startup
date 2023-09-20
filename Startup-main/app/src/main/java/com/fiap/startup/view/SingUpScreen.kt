package com.fiap.startup.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.database.repository.UsuarioRepository
import com.fiap.startup.model.Usuario
import com.fiap.startup.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SingUpScreen(navController: NavController, usuarioRepository: UsuarioRepository) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val emailFocusRequester = remember { FocusRequester() }
    val cpfFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create your account",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next // Define a ação do teclado como "Next"
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    emailFocusRequester.requestFocus()
                }
            )
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(emailFocusRequester),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next // Define a ação do teclado como "Next"
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    cpfFocusRequester.requestFocus()
                }
            )
        )
        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(cpfFocusRequester),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next // Define a ação do teclado como "Next"
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequester.requestFocus()
                }
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(passwordFocusRequester),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next // Define a ação do teclado como "Next"
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    confirmPasswordFocusRequester.requestFocus()
                }
            )
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(confirmPasswordFocusRequester),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done // Define a ação do teclado como "Done" no último campo
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (password == confirmPassword) {
                        val usuario = Usuario(
                            id = 0,
                            nome = name,
                            email = email,
                            cpf = cpf,
                            password = password,
                        )
                        val userId = usuarioRepository.salvar(usuario)

                        if (userId > 0) {
                            // Usuário criado com sucesso, navegue para a próxima tela
                            navController.navigate(AppScreen.MainScreen.route)


                        } else {
                            errorMessage = "Falha ao criar o usuário."
                        }
                    } else {
                        errorMessage = "As senhas não coincidem."
                    }
                    keyboardController?.hide()
                }
            ),

            )
//
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                // Lógica de validação do registro aqui
                if (password == confirmPassword) {
                    val usuario = Usuario(
                        id = 0,
                        nome = name,
                        email = email,
                        cpf = cpf,
                        password = password,
                    )
                    val userId = usuarioRepository.salvar(usuario)

                    if (userId > 0) {
                        // Usuário criado com sucesso, navegue para a próxima tela
                        navController.navigate(AppScreen.MainScreen.route)
                    } else {
                        errorMessage = "Falha ao criar o usuário."
                    }
                } else {
                    errorMessage = "As senhas não coincidem."
                }

                // Fechar o teclado quando o botão é pressionado
                keyboardController?.hide()
            },
            modifier = Modifier.width(260.dp).height(48.dp)
        ) {
            Text("Register")
        }


        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = "Already have an account? Log in",
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate(AppScreen.LoginScreen.route)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)
    SingUpScreen(navController = navController, usuarioRepository = usuarioRepository)
}