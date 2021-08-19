package com.example.restaurantapplicationgraduationproject.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapplicationgraduationproject.databinding.FragmentSearchBinding
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.Restaurant
import com.example.restaurantapplicationgraduationproject.ui.adapters.RestaurantListAdapter
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener
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
        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(query)
                setRestaurants(filterList!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(newText)
                setRestaurants(filterList!!)
                return true
            }

        })
        initViews()
    }
    private fun initViews() {
        _binding.rvSearchNews.adapter = restaurantListAdapter
        _binding.rvSearchNews.layoutManager = LinearLayoutManager(context)
        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)

            }
        })
    }
    private fun setRestaurants(restaurantList: List<Restaurant>) {
        isRestaurantListVisible(restaurantList.isNullOrEmpty().not())
        restaurantListAdapter.setData(restaurantList)
        _binding.rvSearchNews.adapter = restaurantListAdapter
    }
    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.paginationProgressBar.gone()
        _binding.rvSearchNews.isVisible = isVisible

    }
}