package com.example.restaurantapplicationgraduationproject.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapplicationgraduationproject.data.entity.Meal
import com.example.restaurantapplicationgraduationproject.data.entity.Meals
import com.example.restaurantapplicationgraduationproject.data.entity.Restaurants
import com.example.restaurantapplicationgraduationproject.data.entity.RestaurantsItem
import com.example.restaurantapplicationgraduationproject.databinding.ItemMealBinding
import com.example.restaurantapplicationgraduationproject.databinding.ItemRestaurantBinding
import com.example.restaurantapplicationgraduationproject.ui.listeners.IFoodClickListener
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener

class FoodListAdapter: RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {
    var mealList: Meals? = null

    private var listener: IFoodClickListener? = null

    class FoodListViewHolder(val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MealItem: Meal, listener: IFoodClickListener?) {
            binding.mealName.text = MealItem.name
            binding.mealPrice.text = MealItem.price
            binding.itemFoodCardView.setOnClickListener { listener?.onClick(MealItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        return FoodListViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        mealList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }
    override fun getItemCount(): Int {
        return mealList!!.size
    }
    fun setData(newList: Meals?) {
        mealList = newList
        notifyDataSetChanged()
    }
    fun setFoodOnClickListener(listener: IFoodClickListener) {
        this.listener = listener
    }
}