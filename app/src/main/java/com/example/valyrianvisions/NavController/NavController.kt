package com.example.valyrianvisions.NavController

import CartScreen
import CartViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.CommonComps.ProductView
import com.example.valyrianvisions.Screens.DetailedProductView
import com.example.valyrianvisions.Screens.HomeScreen
import com.example.valyrianvisions.Screens.LoginScreen
import com.example.valyrianvisions.Screens.PaintingsScreen
import com.example.valyrianvisions.Screens.ProductsScreen
import com.example.valyrianvisions.Screens.SculpturesScrren
import com.example.valyrianvisions.Screens.SignUpScreen
import com.example.valyrianvisions.Screens.SketchesScreen
import com.example.valyrianvisions.Screens.UserProfile
import com.example.valyrianvisions.Screens.WishlistScreen
import com.example.valyrianvisions.data.DataSource
import com.example.valyrianvisions.data.PaintingsSource
import com.example.valyrianvisions.data.SketchSource

@Composable
fun AppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel, cartViewModel: CartViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginScreen(modifier, navController, authViewModel)
        }

        composable("signup"){
            SignUpScreen(modifier, navController, authViewModel)

        }
        composable("home"){
            HomeScreen(modifier, navController, authViewModel, cartViewModel)
        }
        composable("cart"){
            CartScreen(navController,cartViewModel )
        }
        composable("profile"){
            UserProfile(navController, authViewModel, cartViewModel)
        }
        composable("products"){
            ProductsScreen(navController, cartViewModel)
        }
        composable("paintings"){
            PaintingsScreen(navController,cartViewModel)
        }
        composable("sketches"){
            SketchesScreen(navController,cartViewModel)
        }
        composable("sculptures"){
            SculpturesScrren()
        }
        composable("save"){
            WishlistScreen(navController, cartViewModel)
        }

//        Routes for Detailed Featured Product View
        composable(
            route = "detailedProductView/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = DataSource().loadPictures().find { it.imageResourceId == productId }
            product?.let {
                DetailedProductView(picture = it, navController = navController, cartViewModel)
            }
        }
//        Route for detailed product view
        composable(
            route = "detailed_view/{productId}/{productType}",
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType },
                navArgument("productType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val productType = backStackEntry.arguments?.getString("productType")

            val product = when (productType) {
                "paintings" -> PaintingsSource().loadPaintings().find { it.imageResourceId == productId }
//                "sculptures" -> .loadSculptures().find { it.imageResourceId == productId }
                "sketches" -> SketchSource().loadKSketches().find { it.imageResourceId == productId }
                else -> null
            }

            product?.let {
                ProductView(product = it, navController = navController, cartViewModel)
            }
        }
    })
}