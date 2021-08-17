package com.example.restaurantapplicationgraduationproject.data.entity


import com.google.gson.annotations.SerializedName

data class RestaurantsItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("meals")
    val meals: List<Meal>,
    @SerializedName("name")
    val name: String,
    @SerializedName("score")
    val score: String
)