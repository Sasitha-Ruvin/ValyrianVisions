package com.example.valyrianvisions.NavController

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.Screens.CartScreen
import com.example.valyrianvisions.Screens.DetailedProductView
import com.example.valyrianvisions.Screens.HomeScreen
import com.example.valyrianvisions.Screens.LoginScreen
import com.example.valyrianvisions.Screens.SignUpScreen
import com.example.valyrianvisions.Screens.UserProfile
import com.example.valyrianvisions.data.DataSource

@Composable
fun AppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginScreen(modifier, navController, authViewModel)
        }

        composable("signup"){
            SignUpScreen(modifier, navController, authViewModel)

        }
        composable("home"){
            HomeScreen(modifier, navController, authViewModel)
        }
        composable("cart"){
            CartScreen()
        }
        composable("profile"){
            UserProfile(navController, authViewModel)
        }
        composable(
            route = "detailedProductView/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = DataSource().loadPictures().find { it.imageResourceId == productId }
            product?.let {
                DetailedProductView(picture = it, navController = navController)
            }
        }


    })
}