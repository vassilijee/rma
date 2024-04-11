package com.example.kolokvijum1.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kolokvijum1.cats.details.catDetail
import com.example.kolokvijum1.cats.list.cats


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cats",
    ) {
        cats(route = "cats", onCatClick = { id, imageUrl ->
            val encodedUrl = Uri.encode(imageUrl) // Encode the URL
            navController.navigate("cats/$id/$encodedUrl") // Navigate to cat detail screen with ID and URL
        })
        catDetail(route = "cats/{dataId}/{dataUrl}",
            arguments = listOf(navArgument(name = "dataId") {
                this.type = NavType.StringType
                this.nullable = true
            }, navArgument("dataUrl") {
                this.type = NavType.StringType
                this.nullable = true
            }),
            onClose = {
                navController.navigateUp()
            })
    }
}
