package com.ahmedmadhoun.unitoneassignment.domain.use_case

import com.ahmedmadhoun.unitoneassignment.domain.model.CityModel
import com.ahmedmadhoun.unitoneassignment.domain.model.SliderModel
import com.ahmedmadhoun.unitoneassignment.domain.repository.CitiesRepository
import com.ahmedmadhoun.unitoneassignment.utils.Resource
import javax.inject.Inject

class GetSliderCitiesUseCase @Inject constructor(
    private val repository: CitiesRepository
) {

    suspend operator fun invoke(): List<SliderModel>? {
        return when (val result = repository.getCities()) {
            is Resource.Success -> {
                result.data?.data?.slider?.map {
                    SliderModel(
                        id = it.id,
                        imageURL = it.imageURL,
                        name = it.name
                    )
                }
            }
            is Resource.Error -> {
                null
            }
        }
    }

}