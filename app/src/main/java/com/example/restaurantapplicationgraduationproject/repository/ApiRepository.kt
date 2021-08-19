package com.example.restaurantapplicationgraduationproject.repository

import com.example.restaurantapplicationgraduationproject.model.entity.login.LoginRequest
import com.example.restaurantapplicationgraduationproject.model.entity.order.OrderAddRequest
import com.example.restaurantapplicationgraduationproject.model.entity.profile.UserRequest
import com.example.restaurantapplicationgraduationproject.model.entity.register.RegisterRequest
import com.example.restaurantapplicationgraduationproject.model.local.LocalDataSource
import com.example.restaurantapplicationgraduationproject.model.remote.AuthRemoteDataSource
import com.example.restaurantapplicationgraduationproject.model.remote.RemoteDataSource
import com.example.restaurantapplicationgraduationproject.utils.performAuthTokenNetworkOperation
import com.example.restaurantapplicationgraduationproject.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var authRemoteDataSource: AuthRemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )


    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }


    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }



    fun getRestaurantByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }


    fun getOrder() =
        performNetworkOperation {
            authRemoteDataSource.getOrders()
        }

    fun getUser() = performNetworkOperation {
        authRemoteDataSource.getUser()
    }

    fun updateUser(user : UserRequest) = performNetworkOperation {
        authRemoteDataSource.updateUser(request = user)
    }

    fun postOrder(orderAddRequest: OrderAddRequest) =
        performNetworkOperation {
            authRemoteDataSource.postOrder(orderAddRequest)
        }

    fun logOut() {
        localDataSource.saveToken("")
    }

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

}