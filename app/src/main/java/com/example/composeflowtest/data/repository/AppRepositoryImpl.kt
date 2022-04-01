package com.example.composeflowtest.data.repository

import com.example.composeflowtest.common.Resource
import com.example.composeflowtest.common.safeApiCall
import com.example.composeflowtest.data.remote.BeerApi
import com.example.composeflowtest.domain.model.BeerDomain
import com.example.composeflowtest.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: BeerApi
) : AppRepository {

    override suspend fun getBeerListCoroutines(): Resource<List<BeerDomain>> {
        return safeApiCall { getListBeerCoroutines() }
    }

    override suspend fun getListBeerCoroutines(): Resource<List<BeerDomain>> {
        val response = api.getBeerListCoroutines()
        if (response.isSuccessful) {
            return response.body()?.let {
                return@let Resource.Success(response.body()?.map { it.toDomain() } ?: emptyList())
            } ?: Resource.Error("An unknown error occured", null)
        } else {
            return Resource.Error("An unknown error occured", null)
        }
    }
}
