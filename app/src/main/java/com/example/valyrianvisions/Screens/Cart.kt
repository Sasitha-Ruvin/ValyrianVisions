package com.example.valyrianvisions.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat.Style
import androidx.navigation.NavController
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.R


@Composable
fun CartScreen(navController: NavController){
    ScreenWithTopBarAndBottomNav(navController = navController) {
        Column(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background))
        {
            CarTitle()
            CartItems()
            Spacer(modifier = Modifier.height(12.dp))
            PriceSection()
            ActionButton()
        }

    }

}

@Composable
fun CarTitle(){
Text(text = "Your Cart",
    style = MaterialTheme.typography.titleLarge,
    fontWeight = FontWeight.Bold,
    modifier = Modifier.fillMaxWidth(),
    textAlign = TextAlign.Center)
}
@Composable
fun CartItem(imageRes:Int, name:String, price:String, quntity:String)
{
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = imageRes), contentDescription =null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f))
        {
            Text(text = name, style = MaterialTheme.typography.labelMedium)
            Text(text = price, style = MaterialTheme.typography.labelMedium)
            Text(text = quntity, style = MaterialTheme.typography.labelMedium)
        }

    }

}
@Composable
fun CartItems(){
    Column(modifier = Modifier.padding(horizontal = 16.dp))
    {
        CartItem(imageRes = R.drawable.art3 , name = "The City of Stars", price = "$15", quntity = "x1")
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.scrim, modifier = Modifier.padding(vertical = 8.dp))
        CartItem(imageRes = R.drawable.art1 , name = "The Waves", price = "$15", quntity = "x1")
    }
}

@Composable
fun PriceSection(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(text = "Shipping", style = MaterialTheme.typography.labelMedium)
            Text(text = "$5.00", style = MaterialTheme.typography.labelMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(text = "Sub Total", style = MaterialTheme.typography.labelMedium)
            Text(text = "$40.00", style = MaterialTheme.typography.labelMedium)
        }
    }
}
@Composable
fun ActionButton(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { /* Clear action */ }) {
            Text(text = "Clear", style = MaterialTheme.typography.labelSmall)
        }

        Button(onClick = { /* Buy action */ }) {
            Text(text = "Buy", style = MaterialTheme.typography.labelSmall)
        }
    }
}