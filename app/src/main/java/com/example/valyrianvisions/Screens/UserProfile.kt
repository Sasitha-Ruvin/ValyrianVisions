package com.example.valyrianvisions.Screens

import CartViewModel
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valyrianvisions.Authentications.AuthState
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.R
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav

@Composable
fun UserProfile(navController:NavController,authViewModel: AuthViewModel,cartViewModel: CartViewModel){
    val authState by authViewModel.authstate.observeAsState()
    LaunchedEffect(authState) {
        if(authState is AuthState.Unauthenticated){
            navController.navigate("login"){
                popUpTo("profile"){inclusive =true}
            }

        }
    }
    var startAnimation by remember{ mutableStateOf(false) }


    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val offsetX by animateDpAsState(
        targetValue = if(startAnimation) 0.dp else 3000.dp,
        animationSpec = tween(durationMillis = 400)
    )
    ScreenWithTopBarAndBottomNav(navController = navController, cartViewModel) {innerPadding->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .offset(x = offsetX)
            .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Spacer(modifier = Modifier.height(8.dp))

            Image(painter = painterResource(R.drawable.artist2), contentDescription ="user",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "John Doe",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp))
            {
                UserInfoRow(label = "Username", value = "johnD")
                Spacer(modifier = Modifier.height(10.dp))
                UserInfoRow(label = "Password", value = "************")
                Spacer(modifier = Modifier.height(10.dp))
                UserInfoRow(label = "E-mail", value = "john@mail.com")
                Spacer(modifier = Modifier.height(10.dp))
                UserInfoRow(label = "Contact", value = "0123-456-789")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.weight(0.5f))
                {
                    Text(text = "Edit")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                )
                {
                    Text(text = "Delete")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {authViewModel.signOut()},
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary))
            {
                Text(text = "Logout")
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Handle Orders */ },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.scrim),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Orders",
                    color = MaterialTheme.colorScheme.surface
                )
            }

        }
    }
}

@Composable
fun UserInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 16.sp
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}