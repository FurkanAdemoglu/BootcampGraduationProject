package com.example.restaurantapplicationgraduationproject.ui.mealDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.model.entity.meal.Meal
import com.example.restaurantapplicationgraduationproject.model.entity.meal.MealResponse
import com.example.restaurantapplicationgraduationproject.model.entity.order.OrderAddRequest
import com.example.restaurantapplicationgraduationproject.model.entity.order.OrderAddResponse
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    var meal: Meal? = null

    fun getMealDetails(id: String): LiveData<Resource<MealResponse>> {
        return apiRepository.getMealById(id)
    }

    fun postOrder(orderAddRequest: OrderAddRequest): LiveData<Resource<OrderAddResponse>> =
        apiRepository.postOrder(orderAddRequest)
}