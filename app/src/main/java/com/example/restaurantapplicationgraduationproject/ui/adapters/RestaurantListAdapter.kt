package com.example.restaurantapplicationgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.restaurantapplicationgraduationproject.databinding.ItemRestaurantBinding
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.Restaurant
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

    lateinit var restaurantList: List<Restaurant>

    private var listener: IRestaurantClickListener? = null

    class RestaurantViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(RestaurantItem: Restaurant, listener: IRestaurantClickListener?) {
            binding.restaurantName.text = RestaurantItem.name
            binding.restaurantAddress.text = RestaurantItem.district
            Glide.with(binding.restaurantImage.context)
                .load(RestaurantItem.image).into(binding.restaurantImage)
            binding.itemRestaurantCardView.setOnClickListener { listener?.onClick(RestaurantItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        restaurantList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }
    override fun getItemCount(): Int {
        return restaurantList!!.size
    }
    fun setData(newList: List<Restaurant>?) {
        if (newList != null) {
            restaurantList = newList
        }
        notifyDataSetChanged()
    }
    fun setRestaurantOnClickListener(listener: IRestaurantClickListener) {
        this.listener = listener
    }

}