package com.example.composeflowtest

import com.example.composeflowtest.model.BeerDomain
import com.google.gson.Gson

class AppProvider {

    suspend fun getBeerListCoroutines(): Resource<List<BeerDomain>> {
        return safeApiCall { getListBeerCoroutines() }
    }

    private suspend fun getListBeerCoroutines(): Resource<List<BeerDomain>> {
        val response = AppBackend(Gson()).getBeerListCoroutines()
        if (response.isSuccessful) {
            return response.body()?.let {
                return@let Resource.success(response.body()?.map { it.toDomain() })
            } ?: Resource.error("An unknown error occured", null)
        } else {
            return Resource.error("An unknown error occured", null)
        }
    }
}
