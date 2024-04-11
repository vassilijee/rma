package com.example.kolokvijum1.cats.api

import com.example.kolokvijum1.cats.api.model.CatsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CatsApi {
    @GET("breeds")
    suspend fun getAllCats(): List<CatsApiModel>

    @GET("breeds/{id}")
    suspend fun getCat(
        @Path("id") userId: String,
    ): CatsApiModel

//    @GET("images/{id}")
//    suspend fun getPic(
//        @Path("id") picId: String,
//    ): Image
//
//    @DELETE("users/{id}")
//    suspend fun deleteUser()

}