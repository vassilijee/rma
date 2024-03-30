package com.example.kolokvijum1.compose.model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object CatService {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

}

fun createCatApiService(): CatApi {
    try {
        return CatService.retrofit.create(CatApi::class.java)
    } catch (e: Exception) {
        throw IllegalStateException("Failed to create CatApiService", e)
    }
}



