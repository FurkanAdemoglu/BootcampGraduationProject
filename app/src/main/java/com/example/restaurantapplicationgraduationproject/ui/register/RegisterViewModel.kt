package com.example.restaurantapplicationgraduationproject.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.restaurantapplicationgraduationproject.model.entity.User
import com.example.restaurantapplicationgraduationproject.model.entity.profile.UserRequest
import com.example.restaurantapplicationgraduationproject.model.entity.register.RegisterRequest
import com.example.restaurantapplicationgraduationproject.model.entity.register.RegisterResponse
import com.example.restaurantapplicationgraduationproject.repository.ApiRepository
import com.example.restaurantapplicationgraduationproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun register(
        name: String,
        email: String,
        password: String
    ): LiveData<Resource<RegisterResponse>> {
        val request = RegisterRequest(name, email, password)
        return apiRepository.register(request)
    }

}