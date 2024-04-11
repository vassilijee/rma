package com.example.kolokvijum1.networking

import com.example.kolokvijum1.networking.serialization.AppJson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor {
        val updatedRequest = it.request().newBuilder()
            .addHeader("x-api-key", "live_7oAxDlkoCiQ2ifrqjgfOFVO07dGW7Q3L184egpolf6FWLRapzb8eRcQXv7ZEcLtp")
            .build()
        it.proceed(updatedRequest)
    }
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    )
    .build()


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.thecatapi.com/v1/")
    .client(okHttpClient)
    .addConverterFactory(AppJson.asConverterFactory("application/json".toMediaType()))
    .build()

//api_key=live_7oAxDlkoCiQ2ifrqjgfOFVO07dGW7Q3L184egpolf6FWLRapzb8eRcQXv7ZEcLtp