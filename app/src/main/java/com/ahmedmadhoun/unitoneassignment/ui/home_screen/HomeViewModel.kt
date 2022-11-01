package com.ahmedmadhoun.unitoneassignment.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedmadhoun.unitoneassignment.domain.model.CityModel
import com.ahmedmadhoun.unitoneassignment.domain.model.SliderModel
import com.ahmedmadhoun.unitoneassignment.domain.use_case.GetCitiesUseCase
import com.ahmedmadhoun.unitoneassignment.domain.use_case.GetSliderCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getCitiesUseCase: GetCitiesUseCase,
    val getSliderCitiesUseCase: GetSliderCitiesUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getCities()
        }
        viewModelScope.launch {
            getSliderCities()
        }
    }

    val getCitiesStateFlow: MutableStateFlow<List<CityModel>?> = MutableStateFlow(null)
    val getSliderCitiesStateFlow: MutableStateFlow<List<SliderModel>?> = MutableStateFlow(null)

    private fun getCities() {
        viewModelScope.launch {
            getCitiesStateFlow.value = getCitiesUseCase()
        }
    }

    private fun getSliderCities() {
        viewModelScope.launch {
            getSliderCitiesStateFlow.value = getSliderCitiesUseCase()
        }
    }
}