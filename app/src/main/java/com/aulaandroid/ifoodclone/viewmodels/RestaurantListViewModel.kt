package com.aulaandroid.ifoodclone.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aulaandroid.ifoodclone.data.DataSource
import com.aulaandroid.ifoodclone.data.Restaurant
import kotlin.random.Random

class RestaurantListViewModel(val dataSource: DataSource) : ViewModel() {

    val restaurantsLiveData = dataSource.getRestaurantList()

    fun insertRestaurants(restaurantName: String?, restaurantDescription: String?) {
        if (restaurantName == null || restaurantDescription == null) {
            return
        }


        val id = Random.nextLong()
        val newRestaurant = Restaurant(
            id,
            "Restaurante $id",
            "Descrição restaurante $id"
        )

        dataSource.addRestaurant(newRestaurant)
    }
}

class RestaurantListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RestaurantListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
