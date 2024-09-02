package com.example.valyrianvisions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.valyrianvisions.Screens.HomeScreen
import com.example.valyrianvisions.Screens.LoadScreen
import com.example.valyrianvisions.Screens.LoginScreen
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





