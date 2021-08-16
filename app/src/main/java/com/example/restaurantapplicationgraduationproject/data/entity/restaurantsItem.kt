package com.example.restaurantapplicationgraduationproject.data.entity


import com.google.gson.annotations.SerializedName

data class restaurantsItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("meals")
    val meals: List<Meal>,
    @SerializedName("name")
    val name: String
)