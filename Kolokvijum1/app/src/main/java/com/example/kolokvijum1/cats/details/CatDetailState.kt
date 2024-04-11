package com.example.kolokvijum1.cats.details

import com.example.kolokvijum1.cats.list.model.CatUiModel

data class CatDetailState(
    val loading: Boolean = false,
    val catDetail: CatUiModel = CatUiModel("","", "", "", "")
) {}