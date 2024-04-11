package com.example.kolokvijum1.cats.details

import com.example.kolokvijum1.cats.api.model.Image
import com.example.kolokvijum1.cats.api.model.Weight
import com.example.kolokvijum1.cats.api.model.CatUiModel

data class CatDetailState(
    val loading: Boolean = false,
    val catDetail: CatUiModel = CatUiModel("", "", "", "", "", "", "", Weight("0", "0"), 0, "", Image(""))
) {}