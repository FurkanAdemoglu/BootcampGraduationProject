package com.example.restaurantapplicationgraduationproject.ui.orderList

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.model.entity.order.OrderResponse
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderFragmentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) : ViewModel() {

    fun getOrders(): LiveData<Resource<OrderResponse>> =
        apiRepository.getOrder()

}