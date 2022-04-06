package com.example.composeflowtest.presentation.beerlistdetail

import androidx.compose.runtime.Composable
import com.example.composeflowtest.domain.model.BeerDomain
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination()
@Composable
fun BeerDetailScreen(
    navigator: DestinationsNavigator,
    beer: BeerDomain
) {

}
