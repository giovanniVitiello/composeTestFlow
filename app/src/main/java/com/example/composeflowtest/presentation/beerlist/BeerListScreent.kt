package com.example.composeflowtest.presentation.beerlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composeflowtest.domain.model.BeerDomain
import com.example.composeflowtest.presentation.destinations.BeerDetailScreenDestination
import com.example.composeflowtest.presentation.ui.theme.ComposeFlowTestTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun BeerListScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsState()
    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        LazyColumn {
            items(items = state.value.beers) { beer ->
                Item(beer = beer, onClick = {
                    navigator.navigate(BeerDetailScreenDestination(beer = beer))
                })
            }
        }
    }
}

@Composable
fun Item(beer: BeerDomain, onClick: (BeerDomain) -> Unit) {
    Column(
        Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .clickable { onClick(beer) }
    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(beer.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    color = Color.Black,
                    text = beer.name ?: "",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        color = Color.Black,
                        text = "abv: " + beer.abv,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                    Text(
                        color = Color.Black,
                        text = "ibu: " + beer.ibu,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
        Text(
            color = Color.Black,
            text = beer.description ?: "",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(20.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFlowTestTheme {
        val beer = BeerDomain(
            "birra ceres",
            null,
            "val description: String?",
            "8",
            "7.5",
            null,
            null,
            null
        )
        Item(
            beer = beer,
            onClick = {}
        )
    }
}
