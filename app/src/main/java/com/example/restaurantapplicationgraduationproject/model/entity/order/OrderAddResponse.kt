package com.example.restaurantapplicationgraduationproject.model.entity.order

import com.google.gson.annotations.SerializedName

data class OrderAddResponse(
    @SerializedName("success")
    val success: Boolean
)
