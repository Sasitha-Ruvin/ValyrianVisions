package com.example.valyrianvisions.Screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.R
import com.example.valyrianvisions.data.DataSource
import com.example.valyrianvisions.model.Pictures

@Composable
fun ProductsScreen(navController:NavController){
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
    ScreenWithTopBarAndBottomNav(navController = navController) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .offset(x = offsetX)) {
            SearchBars(search = search, onSearchChange = { search = it })
            Spacer(modifier = Modifier.height(12.dp))
            CategoriesButtons(navController = navController)
            Spacer(modifier = Modifier.height(20.dp))
            ProductGrid(pictures)
            }

        }

    }

@Composable
fun CategoriesButtons(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {navController.navigate("paintings")},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E1A6B))
        ) {
            Text(text = "Paintings")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E1A6B))
        ) {
            Text(text = "Sketches")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E1A6B))
        ) {
            Text(text = "Sculptures")
        }
    }
}

@Composable
fun SearchBars(search: String, onSearchChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(60.dp))
            .clip(RoundedCornerShape(60.dp))
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = search,
            onValueChange = onSearchChange,
            placeholder = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )
    }
}

@Composable
fun ProductGrid(pictures: List<Pictures>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pictures.size) { index ->
            ProductCard(picture = pictures[index])
        }
    }
}
@Composable
fun ProductCard(picture: Pictures) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = picture.imageResourceId),
                contentDescription = stringResource(id = picture.stringResourceId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = picture.stringResourceId),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${picture.price}$",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Buy", color = Color.White)
            }
        }
    }
}
//Main Image Overlay
@Composable
fun MainImage() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.art1),
            contentDescription = "Art One",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        // Overlaying Text
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.immerse_in_art),
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }
}