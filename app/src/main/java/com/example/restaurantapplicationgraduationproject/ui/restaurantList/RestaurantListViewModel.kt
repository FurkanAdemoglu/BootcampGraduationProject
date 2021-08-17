package com.example.restaurantapplicationgraduationproject.ui.restaurantList

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.data.entity.Restaurants
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    fun fetchHospitalList(): LiveData<Resource<Restaurants>> =
        apiRepository.getRestaurants()
}