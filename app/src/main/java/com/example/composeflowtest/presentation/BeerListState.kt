package com.example.composeflowtest.presentation

import com.example.composeflowtest.domain.model.BeerDomain

data class BeerListState(
    val isLoading: Boolean = false,
    val beers: List<BeerDomain> = emptyList(),
    val error: String = ""
)
