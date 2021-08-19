package com.example.restaurantapplicationgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantapplicationgraduationproject.databinding.ItemMealBinding
import com.example.restaurantapplicationgraduationproject.model.entity.meal.Meal
import com.example.restaurantapplicationgraduationproject.ui.listeners.IFoodClickListener

class FoodListAdapter: RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder>() {
    private var foodList = ArrayList<Meal>()

    private var listener: IFoodClickListener? = null

    class FoodListViewHolder(val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal, listener: IFoodClickListener?) {
            binding.mealName.text = meal.name
            binding.mealPrice.text = meal.price
            Glide.with(binding.mealImage.context)
                .load(meal.image).into(binding.mealImage)
            binding.itemFoodCardView.setOnClickListener { listener?.onClick(meal) }
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
        foodList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }
    override fun getItemCount(): Int {
        return foodList.size
    }
    fun setData(newList: ArrayList<Meal>) {
       foodList = newList
        notifyDataSetChanged()
    }
    fun setFoodOnClickListener(listener: IFoodClickListener) {
        this.listener = listener
    }
}