package com.example.restaurantapplicationgraduationproject.ui.restaurantDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.data.entity.Meals
import com.example.restaurantapplicationgraduationproject.data.entity.Restaurants
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    fun fetchMealList(id:String): LiveData<Resource<Meals>> =
        apiRepository.getMeals(id)
}