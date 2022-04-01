package com.example.composeflowtest.domain.repository

import com.example.composeflowtest.common.Resource
import com.example.composeflowtest.domain.model.BeerDomain

interface AppRepository {
    suspend fun getBeerListCoroutines(): Resource<List<BeerDomain>>

    suspend fun getListBeerCoroutines(): Resource<List<BeerDomain>>
}
