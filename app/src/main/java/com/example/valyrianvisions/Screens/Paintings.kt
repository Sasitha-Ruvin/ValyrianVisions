package com.example.valyrianvisions.Screens

import CartViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.valyrianvisions.Animations.LoadingCircle
import com.example.valyrianvisions.CommonComps.ProductsGrid
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.data.DataSource
import com.example.valyrianvisions.data.PaintingsSource
import kotlinx.coroutines.delay

@Composable
fun PaintingsScreen(navController: NavController, cartViewModel: CartViewModel) {
    val paintings = PaintingsSource().loadPaintings()
    var search by remember { mutableStateOf("") }
    var startAnimation by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    LaunchedEffect(Unit)
    {
        delay(1500)
        isLoading = false

    }

    if(isLoading){
        LoadingCircle()
    }else{
        ScreenWithTopBarAndBottomNav(navController = navController, cartViewModel) {
            AnimatedVisibility(
                visible = startAnimation,
                enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                ) {
                    SearchBar(search = search, onSearchChange = { search = it })
                    Spacer(modifier = Modifier.height(12.dp))
                    ProductsGrid(products = paintings)
                }
            }
        }
    }
}