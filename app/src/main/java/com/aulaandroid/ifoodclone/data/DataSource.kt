package com.aulaandroid.ifoodclone.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialRestaurantList = listOf(
        Restaurant(1, "Spoleto", "Massas"),
        Restaurant(2, "Subway", "sei la"),
        Restaurant(3, "Mama Jamma", "teste"),
        Restaurant(4, "Papa Jack", "qualquer coisa"),
        Restaurant(5, "Aprazível", "nao aguento mais"),
    )
    private val restaurantsLiveData = MutableLiveData(initialRestaurantList)

    fun addRestaurant(restaurant: Restaurant) {
        val currentList = restaurantsLiveData.value
        if (currentList == null) {
            restaurantsLiveData.postValue(listOf(restaurant))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, restaurant)
            restaurantsLiveData.postValue(updatedList)
        }
    }

    fun removeRestaurant(restaurant: Restaurant) {
        val currentList = restaurantsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(restaurant)
            restaurantsLiveData.postValue(updatedList)
        }
    }

    fun getRestaurantForId(id: Long): Restaurant? {
        restaurantsLiveData.value?.let { restaurants ->
            return restaurants.firstOrNull { id == id }
        }
        return null
    }

    fun getRestaurantList(): LiveData<List<Restaurant>> {
        return restaurantsLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}