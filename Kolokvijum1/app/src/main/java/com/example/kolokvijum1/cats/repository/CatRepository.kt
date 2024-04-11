package com.example.kolokvijum1.cats.repository

import com.example.kolokvijum1.cats.api.CatsApi
import com.example.kolokvijum1.cats.api.model.CatsApiModel
import com.example.kolokvijum1.cats.model.CatData
import com.example.kolokvijum1.networking.retrofit
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.seconds

object CatRepository {

    //    private var mutablePasswords = SampleData.toMutableList()
    private val cats = MutableStateFlow(listOf<CatData>())
    private val catsApi: CatsApi = retrofit.create(CatsApi::class.java)


    fun allCats(): List<CatData> = cats.value

    /**
     * Simulates api network request which downloads sample data
     * from network and updates passwords in this repository.
     */
//    suspend fun fetchCats() {
//        delay(2.seconds)
//        cats.update { SampleData.toMutableList() }
//    }
    suspend fun fetchAllCats(): List<CatsApiModel> {
        val cats = catsApi.getAllCats()

        // We can here save this locally, or do any other calculations or whatever

        return cats
    }
    /**
     * Simulates api network request which updates single password.
     * It does nothing. Just waits for 1 second.
     */
    suspend fun fetchCatsDetails(catId: String) {
//        delay(1.seconds)
    }

    /**
     * Returns StateFlow which holds all passwords.
     */
    fun observeCats(): Flow<List<CatData>> = cats.asStateFlow()

    /**
     * Returns regular flow with LoginData with given passwordId.
     */
    fun observeCatsDetails(catId: String): Flow<CatData?> {
        return observeCats().map { cats -> cats.find { it.id == catId } }
            .distinctUntilChanged()
    }

    fun getCatById(id: String): CatData? {
        return cats.value.find { it.id == id }
    }

    fun deleteCats(id: String) {
        cats.update { list ->
            val index = list.indexOfFirst { it.id == id }
            if (index != -1) {
                list.toMutableList().apply { removeAt(index) }
            } else {
                list
            }
        }
    }

    fun updateOrInsertCat(id: String, data: CatData) {
        cats.update { list ->
            val index = list.indexOfFirst { it.id == id }
            if (index != -1) {
                list.toMutableList().apply {
                    this[index] = data
                }
            } else {
                list.toMutableList().apply {
                    add(data)
                }
            }
        }
    }
}