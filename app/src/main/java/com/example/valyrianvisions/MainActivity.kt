package com.example.valyrianvisions

import CartViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.SavedStateHandle
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.NavController.AppNavigation
import com.example.valyrianvisions.ui.theme.ValyrianVisionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, true)
        val authViewModel : AuthViewModel by viewModels()

        val cartViewModel : CartViewModel by viewModels()
        setContent {
            ValyrianVisionsTheme {
                AppNavigation(authViewModel = authViewModel, cartViewModel = cartViewModel)
            }
        }
    }
}





