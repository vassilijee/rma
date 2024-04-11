package com.example.kolokvijum1.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kolokvijum1.cats.details.catDetailScreen
import com.example.kolokvijum1.cats.list.catsListScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cats",
    ) {

        catsListScreen(
            route = "cats",
            navController = navController,
        )

        catDetailScreen(
            route = "cats/{dataId}",
            arguments = listOf(
                navArgument(name = "dataId") {
                    this.type = NavType.StringType
                    this.nullable = true
                }
            ),
            navController = navController,
        )
    }
}
