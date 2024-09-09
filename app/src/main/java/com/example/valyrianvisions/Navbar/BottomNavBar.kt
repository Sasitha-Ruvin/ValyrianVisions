package com.example.valyrianvisions.Navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(navController: NavController, modifier: Modifier = Modifier) {
    // Observe the back stack and get the current destination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Home Icon
        IconButton(
            onClick = {
                navController.popBackStack(navController.graph.startDestinationId, false)
                if (currentDestination != "home") {
                    navController.navigate("home")
                }
            }
        ) {
            Icon(
                imageVector = if (currentDestination == "home") Icons.Filled.Home else Icons.Outlined.Home,
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(25.dp))

        // Explore Icon
        IconButton(
            onClick = {
                navController.popBackStack(navController.graph.startDestinationId, false)
                if (currentDestination != "products") {
                    navController.navigate("products")
                }
            }
        ) {
            Icon(
                imageVector = if (currentDestination == "products") Icons.Filled.Store else Icons.Outlined.Store,
                contentDescription = "Explore",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(25.dp))

        // Saved Icon
        IconButton(
            onClick = {
                if (currentDestination != "save") {
                    navController.navigate("save") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            }
        ) {
            Icon(
                imageVector = if (currentDestination == "save") Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = "Saved",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(25.dp))

        // Profile Icon
        IconButton(
            onClick = {
                navController.popBackStack(navController.graph.startDestinationId, false)
                if (currentDestination != "profile") {
                    navController.navigate("profile")
                }
            }
        ) {
            Icon(
                imageVector = if (currentDestination == "profile") Icons.Filled.AccountCircle else Icons.Outlined.Person,
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
