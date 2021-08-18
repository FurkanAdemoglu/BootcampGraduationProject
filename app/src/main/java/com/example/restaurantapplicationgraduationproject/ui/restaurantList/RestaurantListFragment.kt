package com.example.restaurantapplicationgraduationproject.ui.restaurantList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapplicationgraduationproject.R
import com.example.restaurantapplicationgraduationproject.data.entity.RestaurantsItem
import com.example.restaurantapplicationgraduationproject.databinding.FragmentRestaurantListBinding
import com.example.restaurantapplicationgraduationproject.ui.adapters.RestaurantListAdapter
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener
import com.example.restaurantapplicationgraduationproject.utils.Resource
import com.example.restaurantapplicationgraduationproject.utils.gone
import com.example.restaurantapplicationgraduationproject.utils.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var _binding: FragmentRestaurantListBinding
    private val viewModel: RestaurantListViewModel by viewModels()

    private val restaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {

                else -> false
            }
        }



        viewModel.fetchHospitalList().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList", "${it.data}")
                    restaurantListAdapter.setData(it.data)
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
        _binding.hospitalsRecyclerView.adapter = restaurantListAdapter
        _binding.hospitalsRecyclerView.layoutManager = LinearLayoutManager(context)



        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantsItem) {
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)

            }
        })
    }


}