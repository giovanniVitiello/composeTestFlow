package com.example.composeflowtest.presentation.beerlist

import com.example.composeflowtest.domain.model.BeerDomain

data class BeerListState(
    val isLoading: Boolean = false,
    val beers: List<BeerDomain> = emptyList(),
    val error: String = ""
)
