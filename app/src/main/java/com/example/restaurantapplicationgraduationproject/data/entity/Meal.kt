package com.example.restaurantapplicationgraduationproject.data.entity


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("restaurantId")
    val restaurantId: String
)