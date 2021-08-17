package com.example.restaurantapplicationgraduationproject.data.remote

import com.example.restaurantapplicationgraduationproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource(){

    suspend fun fetchRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun fetchMeals(id:String)=getResult { apiService.getMeals(id.toInt()) }



}