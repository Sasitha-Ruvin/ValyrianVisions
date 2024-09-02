package com.example.valyrianvisions.Screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.model.Pictures

@Composable
fun DetailedProductView(
    picture: Pictures,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var startAnimation by remember{ mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val offsetX by animateDpAsState(
        targetValue = if(startAnimation) 0.dp else 3000.dp,
        animationSpec = tween(durationMillis = 400)
    )
    // Quantity state
    var quantity by remember { mutableStateOf(1) }

    ScreenWithTopBarAndBottomNav(navController = navController) { innerPadding->
        Box(modifier = Modifier
            .fillMaxSize()
            .offset(y = offsetX)
            .background(MaterialTheme.colorScheme.onPrimary)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Left,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Product Image
                Image(
                    painter = painterResource(id = picture.imageResourceId),
                    contentDescription = stringResource(id = picture.stringResourceId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = picture.stringResourceId),
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 24.sp
                    )

                    // Wishlist Button
                    Icon(imageVector = Icons.Sharp.FavoriteBorder, contentDescription = "Wishlist")
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Product Price
                Text(
                    text = stringResource(id = picture.priceResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Product Description
                Text(
                    text = "captures the serene and mysterious beauty of the cosmos. The artwork portrays a vast expanse of deep, velvety darkness punctuated by countless twinkling stars.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Quantity Controls
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { if (quantity > 1) quantity-- }) {
                        Text(text = "-", fontSize = 24.sp)
                    }

                    Text(
                        text = "$quantity",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    TextButton(onClick = { quantity++ }) {
                        Text(text = "+", fontSize = 24.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
                    // Add to Cart Button
                    Button(
                        onClick = { /* Add product to cart */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Add to Cart")
                    }
                    // Buy Button
                    Button(
                        onClick = { /* Handle buy now */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 32.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.scrim)
                    ) {
                        Text(text = "Buy")
                    }
                }
            }
        }


    }
}
