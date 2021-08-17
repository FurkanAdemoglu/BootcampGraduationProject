package com.example.restaurantapplicationgraduationproject.ui.listeners

import com.example.restaurantapplicationgraduationproject.data.entity.Meal
import com.example.restaurantapplicationgraduationproject.data.entity.RestaurantsItem

interface IFoodClickListener {
    fun onClick(name: Meal)
}