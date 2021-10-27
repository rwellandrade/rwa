package com.aulaandroid.ifoodclone.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aulaandroid.ifoodclone.App

object DatabaseManager {
    private var dbInstance: AppDb

    init {
        val appContext = App.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext, // contexto global
            AppDb::class.java, // Referência da classe do banco
            "appDb.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getProductsDAO(): ProductDAO = dbInstance.getProductDAO()
}

@Database(entities = [Product::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun getProductDAO(): ProductDAO
}