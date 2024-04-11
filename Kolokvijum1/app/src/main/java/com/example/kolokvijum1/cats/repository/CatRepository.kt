package com.example.kolokvijum1.cats.repository

import com.example.kolokvijum1.cats.api.CatsApi
import com.example.kolokvijum1.cats.api.model.CatsApiModel
import com.example.kolokvijum1.cats.api.model.CatUiModel
import com.example.kolokvijum1.networking.retrofit
import kotlinx.coroutines.flow.MutableStateFlow

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
//        val catsApi = catsApi.getCat(catId)
//        val catsPic = catsApi.getPic(catId)
        val catsApi = catsApi.getCat(catId)

        return catsApi
    }


//    fun observeCats(): Flow<List<CatUiModel>> = cats.asStateFlow()


//    fun observeCatsDetails(catId: String): Flow<CatData?> {
//        return observeCats().map { cats -> cats.find { it.id == catId } }
//            .distinctUntilChanged()
//    }

//    suspend fun getCatById(id: String): CatsApiModel? {
//        return cats.value.find { it.id == id }
//    }

    private fun CatsApiModel.asCatUiModel() = CatUiModel(
        id = this.id,
        name = this.name,
        description = this.description,
        alt_names = this.alt_names ?: "", // Providing a default value for nullable properties
        temperament = this.temperament,
        countryofOrigin = this.countryofOrigin,
        lifeExpectancy = this.lifeExpectancy,
        rare = this.rare,
        wiki = this.wikipedia_url ?: "",
        avgWeight = this.weight,
        image = this.image
    )
}