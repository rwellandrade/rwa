package com.aulaandroid.ifoodclone.network

import android.content.Context
import com.aulaandroid.ifoodclone.App.Companion.API_HOST
import com.aulaandroid.ifoodclone.common.AndroidUtils
import com.aulaandroid.ifoodclone.data.DatabaseManager
import com.aulaandroid.ifoodclone.data.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object ProductService {
    fun getProducts(context: Context): List<Product> {
        val products: ArrayList<Product>
        return if (AndroidUtils.isOnline()) {
            val url = "$API_HOST/products"
            val json = HttpHelper.get(url)
            products = parseJson(json)
            for (d in products) {
                saveOffline(d)
            }
            products
        } else {
            val dao = DatabaseManager.getProductsDAO()
            dao.findAll()
        }

    }

    fun getProduct(id: Long): Product? {
        return if (AndroidUtils.isOnline()) {
            val url = "$API_HOST/products/$id"
            val json = HttpHelper.get(url)
            return parseJson(json)
        } else {
            val dao = DatabaseManager.getProductsDAO()
            dao.getById(id)
        }
    }

    private fun saveOffline(product: Product): Boolean {
        val dao = DatabaseManager.getProductsDAO()

        if (!existsProduct(product)) {
            dao.insert(product)
        }

        return true

    }

    private fun existsProduct(disciplina: Product): Boolean {
        val dao = DatabaseManager.getProductsDAO()
        return dao.getById(disciplina.id) != null
    }

    private inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(json, type)
    }
}
