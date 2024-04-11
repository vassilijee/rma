package com.example.kolokvijum1.cats.api

import com.example.kolokvijum1.cats.api.model.CatsApiModel
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CatsApi {
    @GET("breeds")
    suspend fun getAllCats(): List<CatsApiModel>

    @GET("breeds/{id}")
    suspend fun getCat(
        @Path("id") userId: Int,
    ): CatsApiModel

//    @GET("cats/{id}/albums")
//    suspend fun getUserAlbums(
//        @Path("id") userId: Int,
//    ): List<AlbumApiModel>
//
//    @DELETE("users/{id}")
//    suspend fun deleteUser()

}