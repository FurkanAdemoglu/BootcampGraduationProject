package com.example.restaurantapplicationgraduationproject.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapplicationgraduationproject.data.entity.Restaurants
import com.example.restaurantapplicationgraduationproject.data.entity.RestaurantsItem
import com.example.restaurantapplicationgraduationproject.databinding.ItemRestaurantBinding
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener

class SearchedRestaurantListAdapter:RecyclerView.Adapter<SearchedRestaurantListAdapter.SearchedRestaurantViewHolder>() {

    var restaurantList: Restaurants?=null
    private var listener:IRestaurantClickListener?=null

    class SearchedRestaurantViewHolder(val binding: ItemRestaurantBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(RestaurantsItem: RestaurantsItem, listener: IRestaurantClickListener?){
            binding.restaurantName.text=RestaurantsItem.name
            binding.restaurantAddress.text=RestaurantsItem.location
            binding.itemRestaurantCardView.setOnClickListener { listener?.onClick(RestaurantsItem) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchedRestaurantViewHolder {
        return SearchedRestaurantViewHolder(
            ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchedRestaurantViewHolder, position: Int) {
        restaurantList?.get(position)?.let {
            holder.bind(it,listener)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList!!.size
    }

    fun setData(newList:Restaurants?){
        restaurantList=newList
        notifyDataSetChanged()
    }
    fun setRestaurantOnClickListener(listener: IRestaurantClickListener){
        this.listener=listener
    }
}