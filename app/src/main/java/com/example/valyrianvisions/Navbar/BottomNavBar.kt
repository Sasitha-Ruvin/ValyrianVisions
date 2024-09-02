package com.example.valyrianvisions.Navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNav(navController: NavController, modifier: Modifier){
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly)
    {
        Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home",
            modifier = Modifier
                .clickable { navController.navigate("home") }
                .size(24.dp))
        Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "Explore",
            modifier = Modifier
                .clickable { navController.navigate("explore") }
                .size(24.dp))
        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Saved",
            modifier = Modifier
                .clickable { navController.navigate("saved") }
                .size(24.dp))
        Icon(imageVector = Icons.Outlined.Person, contentDescription = "Profile",
            modifier = Modifier
                .clickable { navController.navigate("profile") }
                .size(24.dp))

    }
}