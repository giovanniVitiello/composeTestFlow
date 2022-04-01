package com.example.composeflowtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeflowtest.model.BeerDomain
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow<Resource<List<BeerDomain>>>(Resource(Status.EMPTY, null, ""))
    var stateFlow = _stateFlow.asStateFlow()

//    fun testFlow() {
//        val startingValue = 10
//        var currentValue = startingValue
//        viewModelScope.launch {
//            while (currentValue >= 0){
//                delay(1000L)
//                _stateFlow.value = currentValue
//                currentValue --
//            }
//        }
//    }

    fun testApi() {
        _stateFlow.value = Resource.loading(null)
        viewModelScope.launch {
            _stateFlow.value = AppProvider().getBeerListCoroutines()
        }
    }
}
