package com.example.restaurantapplicationgraduationproject.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.Restaurant
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.RestaurantListResponse
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    var restaurantList: List<Restaurant>? = null

    fun getRestaurant(): LiveData<Resource<RestaurantListResponse>> =
        apiRepository.getRestaurants()


    fun searchTextOnRestaurantList(text: String?): List<Restaurant>? {
        if (text.isNullOrEmpty())
            return restaurantList

        val filterList: MutableList<Restaurant> = mutableListOf()
        restaurantList?.forEach { restaurant ->
            if (restaurant.name.contains(text, true))
                filterList.add(restaurant)
            else if (restaurant.district.contains(text, true))
                filterList.add(restaurant)
        }
        return filterList
    }
}