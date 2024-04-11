package com.example.kolokvijum1.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatsApiModel(
    val id: String,
    val name: String,
    val description: String,
    val temperament: String,
//    val address: Address,
    @SerialName("alt_names")
    val alt_names: String? = null, // Mark altNames as nullable
)

//@Serializable
//data class Address(
//    val street: String,
//    val city: String,
//    val zipcode: String,
//)

// Json:
//{
//    "id": 1,
//    "name": "Leanne Graham",
//    "username": "Bret",
//    "email": "Sincere@april.biz",
//    "address":
//    {
//        "street": "Kulas Light",
//        "suite": "Apt. 556",
//        "city": "Gwenborough",
//        "zipcode": "92998-3874",
//        "geo": {
//        "lat": "-37.3159",
//        "lng": "81.1496"
//    }
//    },
//    "phone": "1-770-736-8031 x56442",
//    "website": "hildegard.org",
//    "company":
//    {
//        "name": "Romaguera-Crona",
//        "catchPhrase": "Multi-layered client-server neural-net",
//        "bs": "harness real-time e-markets"
//    }
//}