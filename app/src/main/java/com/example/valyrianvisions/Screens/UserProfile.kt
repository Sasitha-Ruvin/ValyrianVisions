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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valyrianvisions.Authentications.AuthState
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.R
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.ViewModels.UserProfileViewModel
import com.example.valyrianvisions.ViewModels.WishListViewModel
import kotlinx.coroutines.launch

@Composable
fun UserProfile(
    navController: NavController,
    authViewModel: AuthViewModel,
    cartViewModel: CartViewModel,
    wishListViewModel: WishListViewModel,
    userProfileViewModel: UserProfileViewModel
) {
    val authState by authViewModel.authstate.observeAsState()
    var isEditMode by remember { mutableStateOf(false) }
    val userProfile = userProfileViewModel.userProfile.value

    var username by remember { mutableStateOf(userProfile.username) }
    var password by remember { mutableStateOf(userProfile.password) }
    var email by remember { mutableStateOf(userProfile.email) }
    var contact by remember { mutableStateOf(userProfile.contact) }

    val coroutineScope = rememberCoroutineScope()

    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
            }
        }
    }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val offsetX by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 3000.dp,
        animationSpec = tween(durationMillis = 400)
    )

    ScreenWithTopBarAndBottomNav(
        navController = navController,
        showbackButton = false,
        cartViewModel = cartViewModel,
        wishListViewModel = wishListViewModel
    ) { innerPadding ->
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (isEditMode) {
                            coroutineScope.launch {
                                userProfileViewModel.updateUserData(username, password, email, contact)
                            }
                        }
                        isEditMode = !isEditMode
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        imageVector = if (isEditMode) Icons.Outlined.Check else Icons.Outlined.Edit,
                        contentDescription = if (isEditMode) "Save" else "Edit"
                    )
                }
            },
            content = { scaffoldPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(scaffoldPadding)
                        .offset(x = offsetX)
                        .verticalScroll(rememberScrollState())
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(R.drawable.artist2),
                        contentDescription = "user",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(16.dp)
                    ) {
                        UserInfoField(label = "Username", value = username, isEditMode = isEditMode, onValueChange = { username = it })
                        Spacer(modifier = Modifier.height(10.dp))
                        UserInfoField(label = "Password", value = password, isEditMode = isEditMode, onValueChange = { password = it })
                        Spacer(modifier = Modifier.height(10.dp))
                        UserInfoField(label = "E-mail", value = email, isEditMode = isEditMode, onValueChange = { email = it })
                        Spacer(modifier = Modifier.height(10.dp))
                        UserInfoField(label = "Contact", value = contact, isEditMode = isEditMode, onValueChange = { contact = it })
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { authViewModel.signOut() },
                            modifier = Modifier.weight(0.5f).width(120.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)
                        ) {
                            Text(text = "Logout")
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.weight(0.5f).width(120.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                        ) {
                            Text(text = "Delete")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        )
    }
}

@Composable
fun UserInfoField(label: String, value: String, isEditMode: Boolean, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf(value) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        if (isEditMode) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    onValueChange(it)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onValueChange(text) }),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            )
        } else {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}
