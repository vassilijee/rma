package com.example.kolokvijum1.repository

import android.util.Log
import com.example.kolokvijum1.compose.model.CatData
import com.example.kolokvijum1.compose.model.createCatApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException


//suspend fun getCatDataList(): List<CatData>? {
//    val catBreeds = fetchCatBreeds() // Call your suspend function to fetch breeds
//    if (catBreeds != null) {
//        val catDataList = catBreeds.map { catBreed ->
//            Log.d("BREED", catBreed.breed)
//            CatData(
//                id = catBreed.id, // Assuming 'id' field exists in the API response
//                breed = catBreed.breed, // Assuming 'name' field exists in the API response
//                // ... other fields based on your API response structure ...
//                pic = "placeholder_image", // Set a placeholder image for now
//                description = catBreed.description
//            )
//        }
//        return catDataList
//    } else {
//        return null // Handle case where fetching breeds failed
//    }
//}

suspend fun getBreedsFromApi(): List<CatData> {
    return withContext(Dispatchers.IO) {
        try {
            val service = createCatApiService()
            service.getCatBreeds("live_7oAxDlkoCiQ2ifrqjgfOFVO07dGW7Q3L184egpolf6FWLRapzb8eRcQXv7ZEcLtp")
        } catch (e: Exception) {
            // Handle error, for now, returning an empty list
            emptyList()
        }
    }
}

var SampleData = mutableListOf(
    CatData(
        id = "abys",
        name = "Abyssinian",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        altNames = "",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle"

    ),
    CatData(
        id = "aege",
        name = "Aegean",
        description = "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding. As a breed, Aegean Cats are rare, although they are numerous on their home islands. They are generally friendly toward people and can be excellent cats for families with children.",
        altNames = "",
        temperament = "Affectionate, Social, Intelligent, Playful, Active"

    ),
    CatData(
        id = "abob",
        name = "American Bobtail",
        description = "American Bobtails are loving and incredibly intelligent cats possessing a distinctive wild appearance. They are extremely interactive cats that bond with their human family with great devotion.",
        altNames = "",
        temperament = "Intelligent, Interactive, Lively, Playful, Sensitive"

    ),
    CatData(
        id = "acur",
        name = "American Curl",
        description = "Distinguished by truly unique ears that curl back in a graceful arc, offering an alert, perky, happily surprised expression, they cause people to break out into a big smile when viewing their first Curl. Curls are very people-oriented, faithful, affectionate soulmates, adjusting remarkably fast to other pets, children, and new situations.",
        altNames = "",
        temperament = "Affectionate, Curious, Intelligent, Interactive, Lively, Playful, Social"

    ),
    CatData(
        id = "asho",
        name = "American Shorthair",
        description = "The American Shorthair is known for its longevity, robust health, good looks, sweet personality, and amiability with children, dogs, and other pets.",
        altNames = "Domestic Shorthair",
        temperament = "Active, Curious, Easy Going, Playful, Calm"

    ),
    CatData(
        id = "awir",
        name = "American Wirehair",
        description = "The American Wirehair tends to be a calm and tolerant cat who takes life as it comes. His favorite hobby is bird-watching from a sunny windowsill, and his hunting ability will stand you in good stead if insects enter the house.",
        altNames = "",
        temperament = "Affectionate, Curious, Gentle, Intelligent, Interactive, Lively, Loyal, Playful, Sensible, Social"

    ),
    CatData(
        id = "amau",
        name = "Arabian Mau",
        description = "Arabian Mau cats are social and energetic. Due to their energy levels, these cats do best in homes where their owners will be able to provide them with plenty of playtime, attention and interaction from their owners. These kitties are friendly, intelligent, and adaptable, and will even get along well with other pets and children.",
        altNames = "Alley cat",
        temperament = "Affectionate, Agile, Curious, Independent, Playful, Loyal"

    ),
    CatData(
        id = "amis",
        name = "Australian Mist",
        description = "The Australian Mist thrives on human companionship. Tolerant of even the youngest of children, these friendly felines enjoy playing games and being part of the hustle and bustle of a busy household. They make entertaining companions for people of all ages, and are happy to remain indoors between dusk and dawn or to be wholly indoor pets.",
        altNames = "Spotted Mist",
        temperament = "Lively, Social, Fun-loving, Relaxed, Affectionate"

    ),
    CatData(
        id = "bali",
        name = "Balinese",
        description = "Balinese are curious, outgoing, intelligent cats with excellent communication skills. They are known for their chatty personalities and are always eager to tell you their views on life, love, and what you’ve served them for dinner. ",
        altNames = "Long-haired Siamese",
        temperament = "Affectionate, Intelligent, Playful"

    ),
    CatData(
        id = "bamb",
        name = "Bambino",
        description = "The Bambino is a breed of cat that was created as a cross between the Sphynx and the Munchkin breeds. The Bambino cat has short legs, large upright ears, and is usually hairless. They love to be handled and cuddled up on the laps of their family members.",
        altNames = "",
        temperament = "Affectionate, Lively, Friendly, Intelligent"

    ),
)