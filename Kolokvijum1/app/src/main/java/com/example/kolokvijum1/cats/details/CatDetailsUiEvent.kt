package com.example.kolokvijum1.cats.details

sealed class CatDetailsUiEvent {
    data class RequestCatDelete(val catId: String) : CatDetailsUiEvent()
}