package com.ahmedmadhoun.unitoneassignment.domain.use_case

import android.util.Log
import com.ahmedmadhoun.unitoneassignment.domain.model.CityModel
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.ahmedmadhoun.unitoneassignment.utils.Resource
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CitiesRepository
) {

    suspend operator fun invoke(): List<CityModel>? {
        return when (val result = repository.getCities()) {
            is Resource.Success -> {
                result.data?.data?.allCities?.map {
                    CityModel(id = it.id, cityName = it.name, image = it.imageURL)
                }
            }
            is Resource.Error -> {
                null
            }
        }
    }

}