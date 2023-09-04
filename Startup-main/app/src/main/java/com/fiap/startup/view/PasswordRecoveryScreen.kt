package com.fiap.startup.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isPasswordResetSent by remember { mutableStateOf(false) }

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.White),
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
                        .padding(16.dp)
        )

        if (isPasswordResetSent) {
            Text(
                    text = "Password reset email sent. Check your inbox.",
                    color = Color.Green,
                    modifier = Modifier.padding(16.dp)
            )
        } else {
            Button(
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
                            .height(48.dp)
            ) {
                Text("Reset Email")
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
                        .padding(top = 20.dp)
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
