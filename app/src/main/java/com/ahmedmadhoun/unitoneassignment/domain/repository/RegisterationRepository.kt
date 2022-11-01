package com.ahmedmadhoun.unitoneassignment.domain.repository

import com.ahmedmadhoun.unitoneassignment.utils.Resource
import com.ahmedmadhoun.unitoneassignment.data.remote.response.CitiesResponse

interface RegisterationRepository {

    suspend fun signInUser(): Resource<CitiesResponse?>

    suspend fun checkUserSignIn(): Resource<CitiesResponse?>



}