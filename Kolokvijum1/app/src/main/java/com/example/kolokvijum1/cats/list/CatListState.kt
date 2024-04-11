package com.example.kolokvijum1.cats.list

import com.example.kolokvijum1.cats.model.CatData

data class CatListState(
    val loading: Boolean = false,
    val cats: List<CatUiModel> = emptyList(),
)
