package com.fiap.startup.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.fiap.startup.R

@Composable
fun BackButton(navController: NavController){
    IconButton(onClick = { navController.navigate(AppScreen.MainScreen.route)
    }) {
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back), contentDescription = null )
    }
}
