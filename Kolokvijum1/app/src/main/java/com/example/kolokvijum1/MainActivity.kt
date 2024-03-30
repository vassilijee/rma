package com.example.kolokvijum1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kolokvijum1.compose.CatBreedScreen
import com.example.kolokvijum1.compose.model.CatData
import com.example.kolokvijum1.repository.getBreedsFromApi
import com.example.kolokvijum1.ui.theme.Kolokvijum1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kolokvijum1Theme {
                val coroutineScope = rememberCoroutineScope()
                var catBreeds by remember { mutableStateOf<List<CatData>>(emptyList()) }

                LaunchedEffect(Unit) {
                    coroutineScope.launch(Dispatchers.IO) {
                        val fetchedBreeds = getBreedsFromApi()
                        catBreeds = fetchedBreeds
                    }
                }

                CatBreedScreen(items = catBreeds, onAddClick = { /*TODO*/ }) {}
            }
        }
    }
}

