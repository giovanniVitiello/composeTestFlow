package com.example.composeflowtest

import com.example.composeflowtest.model.BeerResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class AppBackend(
    gson: Gson
) {
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val privateOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://api.punkapi.com/")
        .client(privateOkHttpClient)
        .build()
        .create(ListingApi::class.java)


    suspend fun getBeerListCoroutines(): Response<List<BeerResponse>> = api.getBeerListCoroutines()


    interface ListingApi {

        @GET("/v2/beers")
        suspend fun getBeerListCoroutines(): Response<List<BeerResponse>>

    }
}
