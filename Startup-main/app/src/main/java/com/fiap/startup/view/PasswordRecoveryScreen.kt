package com.fiap.startup.view

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.database.User
import com.fiap.startup.navigation.AppScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PasswordRecoveryScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isPasswordResetSent by remember { mutableStateOf(false) }
    val emailFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text = "Enter your account",
             fontSize = 16.sp
//            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
//            modifier = Modifier.padding(bottom = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text("Email")
            },
            modifier = Modifier
                .fillMaxWidth().focusRequester(emailFocusRequester), // Define o foco
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // Define a ação do teclado virtual
            ),keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
                //.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isPasswordResetSent) {
            Text(
                text = "Password reset email sent. Check your inbox.",
                color = Color.Green,
                modifier = Modifier.padding(16.dp)
            )
        } else {

            androidx.compose.material.Button(
                onClick = {
                    // Send password reset email using Firebase
                    val auth = FirebaseAuth.getInstance()
                    auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                isPasswordResetSent = true
                            } else {
                                errorMessage = "Password reset email could not be sent. Please check the email address."
                            }
                        }
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(45.dp)
                    .clip(RoundedCornerShape(15.dp),
                ),
                // Aplique um canto arredondado aqui

                // Altere a cor do botão aqui
            ) {
                androidx.compose.material.Text("Recuperar")
            }
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // Add a clickable text to navigate back to the login screen
        Text(
            text = "Back to Login",
            color = Color.Blue,
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController.navigate(AppScreen.LoginScreen.route)
                }
        )
    }
}

@Preview
@Composable
fun RecoveryPasswordPreview(){
    val navController = rememberNavController()
    PasswordRecoveryScreen(navController = navController)
}