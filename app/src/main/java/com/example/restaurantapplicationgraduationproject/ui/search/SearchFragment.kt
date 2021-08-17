package com.example.restaurantapplicationgraduationproject.ui.search

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
import com.example.restaurantapplicationgraduationproject.databinding.FragmentSearchBinding
import com.example.restaurantapplicationgraduationproject.ui.adapters.RestaurantListAdapter
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener
import com.example.restaurantapplicationgraduationproject.ui.restaurantList.RestaurantListFragmentDirections
import com.example.restaurantapplicationgraduationproject.ui.restaurantList.RestaurantListViewModel
import com.example.restaurantapplicationgraduationproject.utils.Resource
import com.example.restaurantapplicationgraduationproject.utils.gone
import com.example.restaurantapplicationgraduationproject.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var _binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    private val restaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var search:String=""
        _binding.button.setOnClickListener {
            search=_binding.etSearch.text.toString()
            viewModel.fetchSearchRestaurants(search).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _binding.paginationProgressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        _binding.paginationProgressBar.gone()
                        Log.v("HospitalList", "${it.data}")
                        restaurantListAdapter.setData(it.data)
                        initViews()
                    }
                    Resource.Status.ERROR -> {
                        _binding.paginationProgressBar.gone()
                        Log.v("RestaurantList", "${it.data}")
                    }
                }
            })
        }

    }

    private fun initViews() {
        _binding.rvSearchNews.adapter = restaurantListAdapter
        _binding.rvSearchNews.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantsItem) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)

            }
        })
    }
}