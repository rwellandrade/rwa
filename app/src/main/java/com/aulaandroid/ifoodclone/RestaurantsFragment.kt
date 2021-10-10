package com.aulaandroid.ifoodclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aulaandroid.ifoodclone.adapters.RestaurantsAdapter
import com.aulaandroid.ifoodclone.common.LoggedFragment
import com.aulaandroid.ifoodclone.data.Restaurant
import com.aulaandroid.ifoodclone.databinding.FragmentRestaurantsBinding
import com.aulaandroid.ifoodclone.viewmodels.RestaurantListViewModel
import com.aulaandroid.ifoodclone.viewmodels.RestaurantListViewModelFactory
import com.aulaandroid.ifoodclone.viewmodels.UserViewModel

class RestaurantsFragment : LoggedFragment() {

    private var _binding: FragmentRestaurantsBinding? = null

    private val userViewModel: UserViewModel by viewModels()

    private val binding get() = _binding!!
    private var restaurantListViewModel: RestaurantListViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        _binding!!.rvRestaurants.layoutManager = LinearLayoutManager(context)
        _binding!!.rvRestaurants.adapter =
            RestaurantsAdapter { restaurant -> adapterOnClick(restaurant) }
    }

    private fun adapterOnClick(restaurant: Restaurant) {
        // TODO: create new fragment with restaurant info
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}