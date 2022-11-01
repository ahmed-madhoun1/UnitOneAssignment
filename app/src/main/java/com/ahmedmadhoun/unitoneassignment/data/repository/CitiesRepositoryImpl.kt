package com.ahmedmadhoun.unitoneassignment.data.repository

import android.util.Log
import com.ahmedmadhoun.unitoneassignment.data.remote.CitiesApi
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.ahmedmadhoun.unitoneassignment.utils.Resource
import com.ahmedmadhoun.unitoneassignment.data.remote.response.CitiesResponse
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val api: CitiesApi
) : CitiesRepository {

    override suspend fun getCities(): Resource<CitiesResponse?> {
        return try {
            val response = api.getCities()
            Resource.Success(response.body())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }


}