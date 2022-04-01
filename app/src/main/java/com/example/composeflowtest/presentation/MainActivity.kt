package com.example.composeflowtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.composeflowtest.common.Resource
import com.example.composeflowtest.domain.model.BeerDomain
import com.example.composeflowtest.presentation.ui.theme.ComposeFlowTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFlowTestTheme {
                // A surface container using the 'background' color from the them
                Box(modifier = Modifier.fillMaxSize()) {
                    CreateLazyList()
                }
            }
        }
    }
}

@Composable
fun CreateLazyList(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.stateFlow.collectAsState(Resource.Empty())
    LazyColumn {
        items(items = state.value.data ?: emptyList()) {
            Item(beer = it)
        }
    }
}


@Composable
fun Item(beer: BeerDomain) {
    Column(Modifier.padding(top = 10.dp, bottom = 10.dp)) {
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
//        Item("Android", 3)
    }
}
