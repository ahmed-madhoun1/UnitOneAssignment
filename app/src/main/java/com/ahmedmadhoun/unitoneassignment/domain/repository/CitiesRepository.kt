package com.ahmedmadhoun.unitoneassignment.domain.repository

import com.ahmedmadhoun.unitoneassignment.utils.Resource
import com.ahmedmadhoun.unitoneassignment.data.remote.response.CitiesResponse

interface CitiesRepository {

    suspend fun getCities(): Resource<CitiesResponse?>



}