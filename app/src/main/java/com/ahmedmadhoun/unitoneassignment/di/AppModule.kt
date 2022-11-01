package com.ahmedmadhoun.unitoneassignment.di

import com.ahmedmadhoun.unitoneassignment.data.remote.CitiesApi
import com.ahmedmadhoun.unitoneassignment.data.repository.CitiesRepositoryImpl
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.ahmedmadhoun.unitoneassignment.utils.BASE_URL
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

    @Singleton
    @Provides
    fun provideRetrofit(): CitiesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CitiesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideVideoModelRepository(api: CitiesApi): CitiesRepository {
        return CitiesRepositoryImpl(api)
    }

}