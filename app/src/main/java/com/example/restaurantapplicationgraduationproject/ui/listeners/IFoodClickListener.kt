package com.example.restaurantapplicationgraduationproject.ui.listeners

import com.example.restaurantapplicationgraduationproject.model.entity.meal.Meal


interface IFoodClickListener {
    fun onClick(name: Meal)
}