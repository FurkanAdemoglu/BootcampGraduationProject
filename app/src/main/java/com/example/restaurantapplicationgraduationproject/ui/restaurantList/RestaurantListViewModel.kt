package com.example.restaurantapplicationgraduationproject.ui.restaurantList

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.Restaurant
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.RestaurantListResponse
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    var restaurantList: List<Restaurant>? = null

    fun getRestaurant(): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getRestaurants()
}