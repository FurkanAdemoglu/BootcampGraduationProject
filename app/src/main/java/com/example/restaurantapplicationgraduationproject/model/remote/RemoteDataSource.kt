package com.example.restaurantapplicationgraduationproject.model.remote

import com.example.restaurantapplicationgraduationproject.model.entity.login.LoginRequest
import com.example.restaurantapplicationgraduationproject.model.entity.register.RegisterRequest
import com.example.restaurantapplicationgraduationproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun getRestaurantsByCuisine(cuisine: String) =
        getResult { apiService.getRestaurantsByCuisine(cuisine) }

    suspend fun getRestaurantById(id: String) = getResult { apiService.getRestaurantById(id) }

    suspend fun getMealById(id: String) = getResult { apiService.getMealById(id) }

    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    suspend fun postRegister(request: RegisterRequest) = getResult {
        apiService.register(request)
    }

}