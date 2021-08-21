package com.example.restaurantapplicationgraduationproject.ui.restaurantDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restaurantapplicationgraduationproject.R
import com.example.restaurantapplicationgraduationproject.databinding.FragmentRestaurantDetailBinding
import com.example.restaurantapplicationgraduationproject.model.entity.meal.Meal
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.Restaurant
import com.example.restaurantapplicationgraduationproject.ui.adapters.FoodListAdapter
import com.example.restaurantapplicationgraduationproject.ui.listeners.IFoodClickListener
import com.example.restaurantapplicationgraduationproject.ui.restaurantList.RestaurantListFragmentDirections
import com.example.restaurantapplicationgraduationproject.utils.Resource
import com.example.restaurantapplicationgraduationproject.utils.gone
import com.example.restaurantapplicationgraduationproject.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private lateinit var _binding: FragmentRestaurantDetailBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private val args: RestaurantDetailFragmentArgs by navArgs()

    private val foodListAdapter = FoodListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRestaurantDetail(args.id!!).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList", "${it.data}")

                    val restaurant = it.data!!.data
                    val options = RequestOptions().placeholder(R.mipmap.no_data)
                    Glide.with(_binding.imageViewRestauarnt.context)
                        .applyDefaultRequestOptions(options)
                        .load(restaurant.image).into(_binding.imageViewRestauarnt)
                    _binding.textViewRestaurantName.text = restaurant.name
                    _binding.textViewRestaurantAddress.text=restaurant.district
                    _binding.textViewRestaurantMenu.text="Menu"
                    foodListAdapter.setData(restaurant.meals)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                    Log.v("RestaurantList", "${it.data}")
                } } }) }
    private fun initViews() {
        _binding.hospitalsRecyclerView.adapter = foodListAdapter
        _binding.hospitalsRecyclerView.layoutManager = LinearLayoutManager(context)
        foodListAdapter.setFoodOnClickListener(object:IFoodClickListener{
            override fun onClick(name: Meal) {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMealDetailFragment(
                        name.id,
                        args.id.toString()
                    )
                findNavController().navigate(action)
            } }) } }