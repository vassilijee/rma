package com.example.kolokvijum1.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatDetailApiModel(
    val id: String,
    val name: String,
    val description: String,
    val temperament: String,
    @SerialName("alt_names")
    val alt_names: String? = null, // Mark altNames as nullable
    val countryofOrigin: String,
    val lifeExpectancy: String,
    val avgWeight: String,
    val rare: String,
    val wiki: String
)
