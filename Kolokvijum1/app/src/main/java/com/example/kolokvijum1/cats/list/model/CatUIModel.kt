package com.example.kolokvijum1.cats.list.model

data class CatUiModel(
    val id: String,
    val name: String,
    val description: String,
    val temperament: String,
    val alt_names: String
) {
    private fun getTemperamentArray(temperamentString: String): List<String> {
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

    fun getDesc250(): String {
        if (description.length > 250) {
            return description.substring(0, 250) + "..."
        }
        return description
    }

//    fun getWithAltNames(): String {
//        if (!alt_names.equals("")) {
//            return name + " - (" + alt_names + ")"
//        } else return name
////        return name;
//    }

}

