package com.example.valyrianvisions.Screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import com.example.valyrianvisions.CommonComps.ProductsGrid
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.data.DataSource
import com.example.valyrianvisions.data.PaintingsSource

@Composable
fun PaintingsScreen(navController: NavController){
    val paintings = PaintingsSource().loadPaintings()
    var search by remember { mutableStateOf("") }
    var startAnimation by remember { mutableStateOf(false) }
    val pictures = DataSource().loadPictures()
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 3000.dp,
        animationSpec = tween(durationMillis = 600)
    )

    ScreenWithTopBarAndBottomNav(navController = navController)
    {
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .offset(x = offsetX)){
            SearchBars(search = search, onSearchChange = { search = it })
            Spacer(modifier = Modifier.height(12.dp))
            ProductsGrid(products = paintings)



        }

    }

}