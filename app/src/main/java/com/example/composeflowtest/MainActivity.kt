package com.example.composeflowtest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.composeflowtest.model.BeerDomain
import com.example.composeflowtest.ui.theme.ComposeFlowTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            val listOfBeers = viewModel.stateFlow.collectAsState(Resource(Status.EMPTY, null, ""))
            viewModel.testApi()
            ComposeFlowTestTheme {
                // A surface container using the 'background' color from the them
                Box(modifier = Modifier.fillMaxSize()) {
                    when(listOfBeers.value.status) {
                        Status.SUCCESS -> listOfBeers.value.data?.let { CreateLazyList(it) }
                        Status.ERROR -> Toast.makeText(this@MainActivity, listOfBeers.value.message, Toast.LENGTH_LONG).show()
                        Status.LOADING -> Toast.makeText(this@MainActivity, "progressBar", Toast.LENGTH_LONG).show()
                        Status.EMPTY -> Log.i("","")
                    }
                }
            }
        }
    }
}

@Composable
fun CreateLazyList(listOfBeers: List<BeerDomain>) {
    LazyColumn {
        items(items = listOfBeers) {
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
