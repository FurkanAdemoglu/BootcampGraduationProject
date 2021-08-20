package com.example.restaurantapplicationgraduationproject.ui.restaurantList

import android.app.ActionBar
import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapplicationgraduationproject.R
import com.example.restaurantapplicationgraduationproject.databinding.FragmentRestaurantListBinding
import com.example.restaurantapplicationgraduationproject.model.entity.restaurant.Restaurant
import com.example.restaurantapplicationgraduationproject.ui.adapters.RestaurantListAdapter
import com.example.restaurantapplicationgraduationproject.ui.listeners.IRestaurantClickListener
import com.example.restaurantapplicationgraduationproject.utils.Resource
import com.example.restaurantapplicationgraduationproject.utils.gone
import com.example.restaurantapplicationgraduationproject.utils.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var _binding: FragmentRestaurantListBinding
    private val viewModel: RestaurantListViewModel by viewModels()
    private var cuisineList: HashMap<String, MaterialButton> = hashMapOf()

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
getRestaurants()
        setCuisineList()
       addListener()

    }

    private fun addListener() {

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {
               val action=RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                   name.id
               )
                findNavController().navigate(action)
            }


        })
        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(query)
                setRestaurants(filterList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(newText)
                setRestaurants(filterList)
                return true
            }

        })
    }
    private fun setCuisineList() {
        val list = resources.getStringArray(R.array.Cuisines).toMutableList()
        list.add(0, getString(R.string.all_restaurants))
        val params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 80, 0)

        list.forEachIndexed { index, item ->
            val button = MaterialButton(requireContext(), null, R.attr.materialButtonOutlinedStyle)
            button.text = item
            button.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (index == 0)
                        R.color.orange
                    else
                        R.color.light_grey
                )
            )
            button.layoutParams = params
            button.isAllCaps = false
            _binding.cuisineTypeLinearLayout.addView(button)
            cuisineList[item] = button
        }
        addCuisineTypesListener()
    }
    private fun getRestaurants(){
        viewModel.getRestaurant().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    viewModel.restaurantList = it.data?.restaurantList
                    setRestaurants(viewModel.restaurantList)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun addCuisineTypesListener() {
        cuisineList.forEach { cuisine ->
            cuisine.value.setOnClickListener {
                //clear other text color
                cuisineList.values.forEach { cuisine ->
                    cuisine.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_grey
                        )
                    )
                }
                cuisine.value.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                _binding.searchView.queryHint = "Search in ${cuisine.key}"
                _binding.searchView.onActionViewCollapsed()
                if (cuisine.key == getString(R.string.all_restaurants))
                    getRestaurants()
                else
                    sendCuisineRequest(cuisine.key)
            }
        }
    }
    private fun setRestaurants(restaurantList: List<Restaurant>?) {
        restaurantListAdapter.setData(restaurantList)
        _binding.restaurantListRecyclerView.adapter = restaurantListAdapter
    }
    private fun sendCuisineRequest(cuisineName: String) {
        viewModel.getRestaurantByCuisine(cuisineName).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    viewModel.restaurantList = response.data?.restaurantList
                    setRestaurants(response.data?.restaurantList)
                }
                Resource.Status.ERROR -> isRestaurantListVisible(false)
            }
        })
    }
    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.restaurantListRecyclerView.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }



    private fun initViews() {
        _binding.restaurantListRecyclerView.adapter = restaurantListAdapter
        _binding.restaurantListRecyclerView.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {

                Log.v("Error", "Error olmuyoooor")
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)
            }
        })
    }

}