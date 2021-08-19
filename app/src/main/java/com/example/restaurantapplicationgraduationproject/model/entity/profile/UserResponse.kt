package com.example.restaurantapplicationgraduationproject.model.entity.profile

import com.example.restaurantapplicationgraduationproject.model.entity.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val user: User,
    @SerializedName("success")
    val success: Boolean
)
