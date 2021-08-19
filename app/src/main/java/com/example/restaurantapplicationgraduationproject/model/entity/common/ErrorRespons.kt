package com.example.restaurantapplicationgraduationproject.model.entity.common

import com.google.gson.annotations.SerializedName

data class ErrorRespons(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
