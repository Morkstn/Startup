package com.fiap.startup


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fiap.startup.navigation.AppScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
                        .padding(8.dp)
        )
        OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
                onClick = {
                    // Authenticate using Firebase
                    //val auth = FirebaseAuth.getInstance()

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // If successful, navigate to the main screen
                                    navController.navigate(AppScreen.MainScreen.route)
                                } else {
                                    // Authentication failed, handle error
                                    val exception = task.exception
                                    errorMessage = when (exception) {
                                        is FirebaseAuthInvalidUserException -> {
                                            // Email is not registered
                                            "Email is not registered."
                                        }

                                        is FirebaseAuthInvalidCredentialsException -> {
                                            // Invalid credentials (e.g., wrong password)
                                            "Invalid credentials. Please check your email and password."
                                        }

                                        else -> {
                                            "Authentication failed. Please try again."
                                        }
                                    }
                                }
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

        /// // Add a clickable text to navigate to the login screen
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
    LoginScreen(navController = navController)
}