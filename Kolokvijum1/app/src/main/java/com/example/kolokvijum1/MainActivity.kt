package com.example.kolokvijum1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kolokvijum1.core.theme.Kolokvijum1Theme
import com.example.kolokvijum1.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kolokvijum1Theme {
                AppNavigation()
            }
        }
    }
}

