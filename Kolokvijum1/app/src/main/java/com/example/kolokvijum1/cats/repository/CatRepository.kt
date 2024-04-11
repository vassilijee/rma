package com.example.kolokvijum1.cats.repository

import com.example.kolokvijum1.cats.api.CatsApi
import com.example.kolokvijum1.cats.api.model.CatsApiModel
import com.example.kolokvijum1.cats.list.model.CatUiModel
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
    private val catsApi: CatsApi = retrofit.create(CatsApi::class.java)
    private val cats = MutableStateFlow(listOf<CatUiModel>())

    fun allCats(): List<CatUiModel> = cats.value

    suspend fun fetchAllCats(): List<CatsApiModel> {
        val fetchedCatsApi = catsApi.getAllCats()
        val catUiModels =
            fetchedCatsApi.map { it.asCatUiModel() } // Convert each CatsApiModel to CatUiModel
        cats.value = catUiModels // Update the MutableStateFlow with the new list of CatUiModel
        return fetchedCatsApi
    }

    suspend fun fetchCatsDetails(catId: String): CatsApiModel {
        val catsApi = catsApi.getCat(catId);
        return catsApi
    }


    fun observeCats(): Flow<List<CatUiModel>> = cats.asStateFlow()


//    fun observeCatsDetails(catId: String): Flow<CatData?> {
//        return observeCats().map { cats -> cats.find { it.id == catId } }
//            .distinctUntilChanged()
//    }

//    suspend fun getCatById(id: String): CatsApiModel? {
//        return cats.value.find { it.id == id }
//    }

    private fun CatsApiModel.asCatUiModel() = CatUiModel(
        id = this.id,
        description = this.description,
        alt_names = this.alt_names ?: "", // Providing a default value for nullable properties
        name = this.name,
        temperament = this.temperament
    )
}