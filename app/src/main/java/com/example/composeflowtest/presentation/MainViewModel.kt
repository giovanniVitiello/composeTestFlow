package com.example.composeflowtest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeflowtest.common.Resource
import com.example.composeflowtest.domain.model.BeerDomain
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

    private val _stateFlow = MutableStateFlow<Resource<List<BeerDomain>>>(Resource.Empty())
    var stateFlow = _stateFlow.asStateFlow()

    init {
        testApi()
    }

    private fun testApi() {
        _stateFlow.value = Resource.Loading()
        viewModelScope.launch {
            when (val result = repository.getBeerListCoroutines()) {
                is Resource.Success -> {
                    _stateFlow.value = result
                }
                is Resource.Error -> {
                    _stateFlow.value = Resource.Error("An unexpected error occured")

                }
                is Resource.Loading -> {
                    _stateFlow.value = Resource.Loading(null)
                }
                is Resource.Empty -> _stateFlow.value = Resource.Empty(null)
            }
        }
    }
}
