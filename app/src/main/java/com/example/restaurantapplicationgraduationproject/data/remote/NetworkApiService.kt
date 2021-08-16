package com.example.restaurantapplicationgraduationproject.data.remote

import com.example.restaurantapplicationgraduationproject.data.entity.restaurants
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {

    @GET("restaurants/{id}/meals")
    suspend fun getRestaurants():Response<restaurants>
}