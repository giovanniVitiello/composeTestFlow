package com.example.composeflowtest.data.remote

import com.example.composeflowtest.data.remote.dto.BeerResponse
import retrofit2.Response
import retrofit2.http.GET

interface BeerApi {

    @GET("/v2/beers")
    suspend fun getBeerListCoroutines(): Response<List<BeerResponse>>

}
