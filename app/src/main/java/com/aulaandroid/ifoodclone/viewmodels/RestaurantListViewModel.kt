package com.aulaandroid.ifoodclone.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aulaandroid.ifoodclone.data.DataSource
import com.aulaandroid.ifoodclone.data.Product
import kotlinx.coroutines.launch
import kotlin.random.Random

class RestaurantListViewModel(val dataSource: DataSource) : ViewModel() {

//    val restaurantsLiveData = dataSource.getRestaurantList()

    private fun getRestaurantList() {
        viewModelScope.launch {
//            val listResult = UserApiService.IUserApiService.getUser()
        }
    }

    fun insertRestaurants(restaurantName: String?, restaurantDescription: String?) {
        if (restaurantName == null || restaurantDescription == null) {
            return
        }


        val id = Random.nextLong()

//        dataSource.addRestaurant(newRestaurant)
    }
}

