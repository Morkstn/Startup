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
import com.fiap.startup.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, usuarioRepository: UsuarioRepository) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter your account",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text("Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(passwordFocusRequester),
         keyboardOptions = KeyboardOptions(
             imeAction = ImeAction.Done
         ),
         keyboardActions = KeyboardActions(
             onDone = {

                 keyboardController?.hide()
             }
         )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Verificar o login
                val isValid = usuarioRepository.isValidLogin(email, password)

                if (isValid) {
                    // Login válido, navegue para a tela principal
                    navController.navigate(AppScreen.MainScreen.route)
                } else {
                    // Autenticação falhou, exiba uma mensagem de erro
                    errorMessage = "Authentication failed. Please check your email and password."
                }
            },
            modifier = Modifier
                .width(260.dp)
                .height(48.dp)

        ) {
            Text("Login")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(
            text = "Forget your account?",
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate(AppScreen.PasswordRecoveryScreen.route)
                }
        )


    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController() // Assuming you import rememberNavController
    val context = LocalContext.current
    val usuarioRepository = UsuarioRepository(context)
    LoginScreen(navController = navController, usuarioRepository = usuarioRepository)
}
