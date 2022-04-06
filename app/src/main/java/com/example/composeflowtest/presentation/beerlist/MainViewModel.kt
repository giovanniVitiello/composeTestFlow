package com.example.composeflowtest.presentation.beerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeflowtest.common.Resource
import com.example.composeflowtest.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(BeerListState())
    var stateFlow = _stateFlow.asStateFlow()

    init {
        testApi()
    }

    private fun testApi() {
        _stateFlow.value = BeerListState(isLoading = true)
        viewModelScope.launch {
            when (val result = repository.getBeerListCoroutines()) {
                is Resource.Success -> {
                    _stateFlow.value = BeerListState(beers = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateFlow.value = BeerListState(error = "An unexpected error occured")

                }
                is Resource.Loading -> {
                    _stateFlow.value = BeerListState(isLoading = true)
                }
            }
        }
    }
}
