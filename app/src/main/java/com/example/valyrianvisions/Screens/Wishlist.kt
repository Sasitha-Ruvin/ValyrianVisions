package com.example.valyrianvisions.Screens

import CartViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav

@Composable
fun WishlistScreen(navController: NavController, cartViewModel: CartViewModel){
    ScreenWithTopBarAndBottomNav(navController = navController, cartViewModel = cartViewModel) {

    }
}