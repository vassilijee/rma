//package com.example.kolokvijum1
//
//import com.example.kolokvijum1.cats.model.createCatApiService
//import kotlinx.coroutines.runBlocking
//import java.io.IOException
//
//
//fun main() {
//    runBlocking {
//        try {
//            val service = createCatApiService()
//            val breeds = service.getCatBreeds("live_7oAxDlkoCiQ2ifrqjgfOFVO07dGW7Q3L184egpolf6FWLRapzb8eRcQXv7ZEcLtp")
//            breeds.forEach { breed ->
////                TODO
//                println("Cat breed: ${breed.name}, altNames: ${breed.altNames}")
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//}
//
