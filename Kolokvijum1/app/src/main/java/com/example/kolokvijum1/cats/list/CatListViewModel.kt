package com.example.kolokvijum1.cats.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolokvijum1.cats.api.model.CatsApiModel
import com.example.kolokvijum1.cats.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.nio.file.Files.copy

class CatListViewModel(private val repository: CatRepository = CatRepository) : ViewModel() {
    private val _state = MutableStateFlow(CatListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatListState.() -> CatListState) = _state.getAndUpdate(reducer)

    init {
        //observeCats()
        fetchCats()
    }

    /**
     * This will observe all passwords and update state whenever
     * underlying data changes. We are using viewModelScope which
     * will cancel the subscription when view model dies.
     */
//    private fun observeCats() {
//        // We are launching a new coroutine
//        viewModelScope.launch {
//            // Which will observe all changes to our passwords
//            repository.observeCats().collect {
//                setState { copy(cats = it) }
//            }
//        }
//    }

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
//                setState { copy(error = CatListState.ListError.ListUpdateFailed(cause = error)) }
            } finally {
                setState { copy(loading = false) }
            }
        }
    }
    private fun CatsApiModel.asCatUiModel() = CatUiModel (
        id = this.id,
        name = this.name,
        description = this.description,
        //alt_names = this.alt_names,
//        temperament = this.temperament
    )
}