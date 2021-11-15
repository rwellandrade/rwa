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
            val url = "$API_HOST/v1/products"
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

//    fun getDisciplina(context: Context, id: Long): Product? {
//        if (AndroidUtils.isOnline()) {
//            val url = "$host/disciplinas/${id}"
//            val json = HttpHelper.get(url)
//            val disciplina = parserJson<Product>(json)
//            return disciplina
//        } else {
//            val dao = DatabaseManager.getDisciplinaDAO()
//            val disciplina = dao.getById(id)
//            return disciplina
//        }
//
//    }
//
//    fun save(disciplina: Disciplina): Response {
//        if (AndroidUtils.isOnline()) {
//            val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
//            return parserJson(json)
//        } else {
//            saveOffline(disciplina)
//            return Response("OK", "Disciplina salva no dispositivo")
//        }
//    }

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
//
//    fun delete(disciplina: Disciplina): Response {
//        if (AndroidUtils.isOnline()) {
//            val url = "$host/disciplinas/${disciplina.id}"
//            val json = HttpHelper.delete(url)
//
//            return parserJson(json)
//        } else {
//            val dao = DatabaseManager.getDisciplinaDAO()
//            dao.delete(disciplina)
//            return Response(status = "OK", msg = "Dados salvos localmente")
//        }
//
//    }

    private inline fun <reified T> parseJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(json, type)
    }
}
