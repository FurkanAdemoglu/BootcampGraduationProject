package com.example.restaurantapplicationgraduationproject.model.entity

import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("ingredient")
    var ingredient: String,
    @SerializedName("includes")
    var includes: Boolean
)
