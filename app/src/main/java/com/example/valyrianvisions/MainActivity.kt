package com.example.valyrianvisions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.NavController.AppNavigation
import com.example.valyrianvisions.ui.theme.ValyrianVisionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            ValyrianVisionsTheme {
                AppNavigation(authViewModel = authViewModel)
            }
        }
    }
}





