package com.example.kolokvijum1.cats.list

import com.example.kolokvijum1.cats.list.model.CatUiModel


data class CatListState(
    val loading: Boolean = false,
    val cats: List<CatUiModel> = emptyList(),
)
