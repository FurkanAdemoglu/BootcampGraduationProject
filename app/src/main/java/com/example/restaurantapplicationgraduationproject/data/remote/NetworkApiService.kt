package com.example.restaurantapplicationgraduationproject.data.remote

import com.example.restaurantapplicationgraduationproject.data.entity.Meals
import com.example.restaurantapplicationgraduationproject.data.entity.Restaurants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {

    @GET("restaurants/")
    suspend fun getRestaurants():Response<Restaurants>

    @GET("restaurants/{id}/males")
    suspend fun getMeals(@Path("id")id:Int):Response<Meals>

    @GET("restaurants")
    suspend fun searchRestaurants(@Query("search")searchKey:String):Response<Restaurants>



}