package com.aulaandroid.ifoodclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aulaandroid.ifoodclone.adapters.ProductsAdapter
import com.aulaandroid.ifoodclone.common.LoggedFragment
import com.aulaandroid.ifoodclone.data.Product
import com.aulaandroid.ifoodclone.databinding.FragmentRestaurantsBinding
import com.aulaandroid.ifoodclone.network.ProductService
import com.aulaandroid.ifoodclone.viewmodels.RestaurantListViewModel
import com.aulaandroid.ifoodclone.viewmodels.UserViewModel

class RestaurantsFragment : LoggedFragment() {

    private var _binding: FragmentRestaurantsBinding? = null

    private val userViewModel: UserViewModel by viewModels()

    private val binding get() = _binding!!
    private var restaurantListViewModel: RestaurantListViewModel? = null
    private var restaurants = listOf<Product>()

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
    }

    override fun onResume() {
        super.onResume()
        // task para recuperar as disciplinas
        taskRestaurants()
    }


    fun taskRestaurants() {
        Thread {
            this.restaurants = ProductService.getProducts(requireContext())
            requireActivity().runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                _binding!!.rvRestaurants.adapter = ProductsAdapter(this.restaurants) { restaurant ->
                    adapterOnClick(restaurant)
                }
                // enviar notificação
//                enviaNotificacao(this.disciplinas.get(0))

            }
        }.start()

    }

    private fun adapterOnClick(product: Product) {
        // TODO: create new fragment with restaurant info
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}