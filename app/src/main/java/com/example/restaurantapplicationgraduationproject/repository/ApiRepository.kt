package com.example.restaurantapplicationgraduationproject.repository

import com.example.restaurantapplicationgraduationproject.data.remote.RemoteDataSource
import com.example.restaurantapplicationgraduationproject.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
){

    fun getRestaurants() = performNetworkOperation {
        remoteDataSource.fetchHospitals()
    }


}