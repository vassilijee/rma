package com.example.kolokvijum1.compose.model

import com.squareup.moshi.Json

data class CatData(
    @Json(name = "id")
    val id: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "alt_names")
    val altNames: String,

    @Json(name = "temperament")
    val temperament: String
) {
    fun getDesc250(): String {
        if (description.length > 250) {
            return description.substring(0, 250) + "..."
        }
        return description
    }

    fun getWithAltNames(): String {
//        TODO
//        if (!altNames.equals("")) {
//            return name + " - (" + altNames + ")"
//        } else return name
        return name;
    }

    fun getTemperamentArray(temperamentString: String): List<String> {
        // Split the string by comma
        val temperamentList = temperamentString.split(",")

        // Remove leading/trailing whitespaces (optional)
        val trimmedTemperaments = temperamentList.map { temp -> temp.trim() }

        return trimmedTemperaments
    }

    fun getTemperamentArray3(temperamentString: String): List<String> {
        // Split the string by comma
        val temperamentList = getTemperamentArray(temperamentString)

        return temperamentList.take(3)
    }
}