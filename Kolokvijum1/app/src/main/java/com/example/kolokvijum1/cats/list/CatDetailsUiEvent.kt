package com.example.kolokvijum1.cats.list

sealed class CatDetailsUiEvent {
    data class RequestCatDelete(val catId: String) : CatDetailsUiEvent()
}