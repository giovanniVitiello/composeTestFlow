package com.example.composeflowtest.presentation.beerlistdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeflowtest.domain.model.BeerDomain
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BeerDetailScreen(
    navigator: DestinationsNavigator,
    beer: BeerDomain
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Text(
            text = "First Brewed: " + beer.firstBrewed
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .padding(10.dp)
        )
        Text(
            text = "Food Pairing: " + beer.foodPairing
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .padding(10.dp)
        )
        Text(
            text = "Brewer Tips: : " + beer.brewersTips
        )
    }
}
