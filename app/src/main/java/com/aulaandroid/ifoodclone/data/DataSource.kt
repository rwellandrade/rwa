package com.aulaandroid.ifoodclone.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
//    private val initialRestaurantList = listOf(
//        Product(1, "Spoleto", "Massas"),
//        Product(2, "Subway", "sei la"),
//        Product(3, "Mama Jamma", "teste"),
//        Product(4, "Papa Jack", "qualquer coisa"),
//        Product(5, "Aprazível", "nao aguento mais"),
//    )
//    private val restaurantsLiveData = MutableLiveData(initialRestaurantList)

//    fun addRestaurant(product: Product) {
//        val currentList = restaurantsLiveData.value
//        if (currentList == null) {
//            restaurantsLiveData.postValue(listOf(product))
//        } else {
//            val updatedList = currentList.toMutableList()
//            updatedList.add(0, product)
//            restaurantsLiveData.postValue(updatedList)
//        }
//    }
//
//    fun removeRestaurant(product: Product) {
//        val currentList = restaurantsLiveData.value
//        if (currentList != null) {
//            val updatedList = currentList.toMutableList()
//            updatedList.remove(product)
//            restaurantsLiveData.postValue(updatedList)
//        }
//    }
//
//    fun getRestaurantForId(id: Long): Product? {
//        restaurantsLiveData.value?.let { restaurants ->
//            return restaurants.firstOrNull { id == id }
//        }
//        return null
//    }
//
//    fun getRestaurantList(): LiveData<List<Product>> {
//        return restaurantsLiveData
//    }
//
//    companion object {
//        private var INSTANCE: DataSource? = null
//
//        fun getDataSource(resources: Resources): DataSource {
//            return synchronized(DataSource::class) {
//                val newInstance = INSTANCE ?: DataSource(resources)
//                INSTANCE = newInstance
//                newInstance
//            }
//        }
//    }
}