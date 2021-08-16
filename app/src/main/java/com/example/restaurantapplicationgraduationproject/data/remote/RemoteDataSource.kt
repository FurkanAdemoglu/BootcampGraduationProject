package com.example.restaurantapplicationgraduationproject.data.remote

import com.example.restaurantapplicationgraduationproject.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource(){

    suspend fun fetchHospitals() = getResult { apiService.getRestaurants() }


}