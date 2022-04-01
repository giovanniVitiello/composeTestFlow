package com.example.composeflowtest.di

import com.example.composeflowtest.common.Constants
import com.example.composeflowtest.data.remote.BeerApi
import com.example.composeflowtest.data.repository.AppRepositoryImpl
import com.example.composeflowtest.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: BeerApi): AppRepository {
        return AppRepositoryImpl(api)
    }
}
