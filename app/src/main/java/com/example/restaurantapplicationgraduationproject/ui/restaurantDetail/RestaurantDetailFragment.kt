package com.example.restaurantapplicationgraduationproject.ui.restaurantDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapplicationgraduationproject.databinding.FragmentRestaurantDetailBinding
import com.example.restaurantapplicationgraduationproject.model.entity.meal.Meal
import com.example.restaurantapplicationgraduationproject.ui.adapters.FoodListAdapter
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
        viewModel.fetchMealList(args.id!!).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList", "${it.data}")

                    foodListAdapter.setData(arrayListOf(it.data!!.data))
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                    Log.v("RestaurantList", "${it.data}")
                }
            }
        })
    }

    private fun initViews() {
        _binding.hospitalsRecyclerView.adapter = foodListAdapter
        _binding.hospitalsRecyclerView.layoutManager = LinearLayoutManager(context)


    }


}