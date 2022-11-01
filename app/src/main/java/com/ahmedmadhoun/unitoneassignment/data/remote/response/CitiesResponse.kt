package com.ahmedmadhoun.unitoneassignment.data.remote.response

data class CitiesResponse(
    val data: Data,
    val errors: List<String>,
    val message: String,
    val status: Boolean
)