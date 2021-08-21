package com.example.restaurantapplicationgraduationproject.ui.orderList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantapplicationgraduationproject.databinding.ItemMyordersBinding
import com.example.restaurantapplicationgraduationproject.model.entity.order.Order
import java.text.SimpleDateFormat

class OrderFragmentAdapter :
    RecyclerView.Adapter<OrderFragmentAdapter.OrderListViewHolder>() {
    var myOrderList = ArrayList<Order>()

    class OrderListViewHolder(val binding: ItemMyordersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun setItem(order: Order) {
            Glide.with(binding.orderedMealImage.context)
                .load(order.meal.image)
                .into(binding.orderedMealImage)
            binding.orderedRestaurantName.text = order.restaurant.name
            binding.orderedMealName.text = order.meal.name
            binding.orderedMealDate.text = SimpleDateFormat("dd/MM/yyyy").format(order.createdDate).toString()
            binding.orderedMealPrice.text = "Price:"+order.meal.price.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        return OrderListViewHolder(
            ItemMyordersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val item = myOrderList[position]
        holder.setItem(item)
    }
    fun setOrderList(list: ArrayList<Order>) {
        this.myOrderList = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = myOrderList.size
}