package com.fiap.startup.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fiap.startup.R
import com.fiap.startup.navigation.AppScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController){
    
    LaunchedEffect(key1 = true){
        delay(5000)
        navController.popBackStack()
        navController.navigate(AppScreen.SingUpScreen.route)
    }

    Splash()
}

@Composable
fun Splash(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_mjv),
            contentDescription = "Logo MJV",
            modifier = Modifier.size(150.dp, 150.dp)
        )

        Text(
            text = "MJV Corporation",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    Splash()
}