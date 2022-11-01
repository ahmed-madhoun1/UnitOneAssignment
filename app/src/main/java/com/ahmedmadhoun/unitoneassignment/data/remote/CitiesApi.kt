package com.ahmedmadhoun.unitoneassignment.data.remote

import com.ahmedmadhoun.unitoneassignment.data.remote.response.CitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CitiesApi {

    @GET("home/public")
    suspend fun getCities(): Response<CitiesResponse>

}