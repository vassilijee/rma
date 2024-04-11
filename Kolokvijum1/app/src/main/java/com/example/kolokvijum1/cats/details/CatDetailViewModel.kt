package com.example.kolokvijum1.cats.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolokvijum1.cats.api.model.CatsApiModel
import com.example.kolokvijum1.cats.api.model.CatUiModel
import com.example.kolokvijum1.cats.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatDetailViewModel(
    private val catId: String,
    private val catRepository: CatRepository = CatRepository,
    ) : ViewModel() {
    private val _state = MutableStateFlow(CatDetailState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatDetailState.() -> CatDetailState) = _state.update(reducer)

    init {
        fetchCat()
    }

    private fun fetchCat() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val cat = withContext(Dispatchers.IO) {
                    catRepository.fetchCatsDetails(catId = catId)
                }
                val catUiModel = cat.asCatUiModel() // Convert CatsApiModel to CatUiModel
                setState { copy(catDetail = catUiModel) }
            } catch (error: Exception) {
                // TODO Handle error
            }
            setState { copy(loading = false) }
        }
    }



    private fun CatsApiModel.asCatUiModel() = CatUiModel(
        id = this.id,
        description = this.description,
        alt_names = this.alt_names ?: "", // Providing a default value for nullable properties
        name = this.name,
        temperament = this.temperament,
        countryofOrigin = this.countryofOrigin,
        lifeExpectancy = this.lifeExpectancy,
        rare = this.rare,
        wiki = this.wikipedia_url ?: "",
        avgWeight = this.weight,
        image = this.image
    )
}