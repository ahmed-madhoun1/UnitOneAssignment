package com.ahmedmadhoun.unitoneassignment.data.repository

import android.util.Log
import com.ahmedmadhoun.unitoneassignment.data.remote.CitiesApi
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.ahmedmadhoun.unitoneassignment.utils.Resource
import com.ahmedmadhoun.unitoneassignment.data.remote.response.CitiesResponse
import com.ahmedmadhoun.unitoneassignment.domain.repository.RegisterationRepository
import javax.inject.Inject

class RegisterationRepositoryImpl @Inject constructor(
    private val api: CitiesApi
) : RegisterationRepository {
    override suspend fun signInUser(): Resource<CitiesResponse?> {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserSignIn(): Resource<CitiesResponse?> {
        TODO("Not yet implemented")
    }


}