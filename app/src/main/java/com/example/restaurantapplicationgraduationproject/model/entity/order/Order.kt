package com.example.restaurantapplicationgraduationproject.model.entity.order

import com.example.restaurantapplicationgraduationproject.model.entity.meal.Meal
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.OrderRestaurant
import com.google.gson.annotations.SerializedName
import java.util.*

data class Order(
    @SerializedName("_id")
    val id: String,
    @SerializedName("createdDate")
    val createdDate: Date,
    @SerializedName("meal")
    val meal: Meal,
    @SerializedName("restaurant")
    val restaurant: OrderRestaurant,
    @SerializedName("price")
    val price: Double
)
