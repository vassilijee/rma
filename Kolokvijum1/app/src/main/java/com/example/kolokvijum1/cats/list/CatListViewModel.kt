package com.example.kolokvijum1.cats.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolokvijum1.cats.api.model.CatsApiModel
import com.example.kolokvijum1.cats.api.model.CatUiModel
import com.example.kolokvijum1.cats.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatListViewModel(private val repository: CatRepository = CatRepository) : ViewModel() {
    private val _state = MutableStateFlow(CatListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatListState.() -> CatListState) = _state.getAndUpdate(reducer)

    init {
        observeCats()
        fetchCats()
    }

    /**
     * This will observe all passwords and update state whenever
     * underlying data changes. We are using viewModelScope which
     * will cancel the subscription when view model dies.
     */
    private fun observeCats() {
        // We are launching a new coroutine
        viewModelScope.launch {
            // Which will observe all changes to our passwords
//            repository.observeCats().collect {
//                setState { copy(cats = it) }
//            }
//            ??
            setState { copy(cats = repository.allCats()) }
        }
    }

    /**
     * Fetches passwords from simulated api endpoint and
     * replaces existing passwords with "downloaded" passwords.
     */
    private fun fetchCats() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            try {
                val cats = withContext(Dispatchers.IO) {
                    repository.fetchAllCats().map { it.asCatUiModel() }
                }
                setState { copy(cats = cats) }
            } catch (error: Exception) {
                Log.e("CatViewModel", "Error fetching cats", error)
            } finally {
                setState { copy(loading = false) }
            }
        }
    }

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