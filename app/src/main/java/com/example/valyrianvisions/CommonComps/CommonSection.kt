package com.example.valyrianvisions.CommonComps

import CartViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valyrianvisions.Navbar.BottomNav
import com.example.valyrianvisions.TopBar.TopBar

@Composable
fun ScreenWithTopBarAndBottomNav(
    navController: NavController,
    cartViewModel: CartViewModel,
    content: @Composable (Modifier) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        TopBarSection(navController = navController, cartViewModel)

        // Main Content
        Box(modifier = Modifier.weight(1f).background(MaterialTheme.colorScheme.background)) {
            content(Modifier.fillMaxSize())
        }

        // Bottom Navigation Bar
        BottomNavSection(navController = navController)
    }
}



@Composable
fun BottomNavSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        BottomNav(navController = navController, modifier = Modifier,)
    }
}


@Composable
fun TopBarSection(navController: NavController, cartViewModel: CartViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        TopBar(navController = navController, cartViewModel )
    }
}
