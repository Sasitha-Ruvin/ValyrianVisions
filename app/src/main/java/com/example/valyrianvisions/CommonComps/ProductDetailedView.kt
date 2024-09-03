package com.example.valyrianvisions.Screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
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
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 3000.dp,
        animationSpec = tween(durationMillis = 400)
    )
    // Quantity state
    var quantity by remember { mutableStateOf(1) }
    // Rating state
    var rating by remember { mutableStateOf(0) }

    ScreenWithTopBarAndBottomNav(navController = navController) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = offsetX)
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.height(35.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(vertical = 12.dp),

            ) {
                Spacer(modifier = Modifier.height(35.dp))

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
                    TextButton(onClick = { /*TODO*/ })
                    {
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Wishlist")

                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "by",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.labelMedium
                    )
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

                // Star Rating
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    (1..5).forEach { index ->
                        Icon(
                            imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = "Star Rating",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    rating = index
                                }
                        )
                    }
                }

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

                    Spacer(modifier = Modifier.width(16.dp))

                    // Add to Cart Button
                    Button(
                        onClick = { /* Add product to cart */ },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(text = "Add to Cart")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }
}

