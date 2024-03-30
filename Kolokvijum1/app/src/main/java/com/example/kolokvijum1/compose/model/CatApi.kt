package com.example.kolokvijum1.compose.model

import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("breeds")
    suspend fun getCatBreeds(
        @Query("api_key") parameterValue: String
    ): List<CatData>
}
