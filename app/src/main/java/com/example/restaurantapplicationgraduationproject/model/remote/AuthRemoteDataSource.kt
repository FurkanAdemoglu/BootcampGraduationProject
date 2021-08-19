package com.example.restaurantapplicationgraduationproject.model.remote

import com.example.restaurantapplicationgraduationproject.model.entity.order.OrderAddRequest
import com.example.restaurantapplicationgraduationproject.model.entity.profile.UserRequest
import com.example.restaurantapplicationgraduationproject.utils.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthAPIService) :
    BaseDataSource() {


    suspend fun getOrders() = getResult { authAPIService.getOrders() }

    suspend fun updateUser(request : UserRequest) = getResult { authAPIService.updateUser(request) }

    suspend fun getUser() = getResult { authAPIService.getUser() }

    suspend fun postOrder(orderAddRequest: OrderAddRequest) = getResult {
        authAPIService.postOrder(orderAddRequest)
    }

}