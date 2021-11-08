package com.aulaandroid.ifoodclone.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity
class Product(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val category: String,
) : BaseData(), Serializable {
    override fun toString(): String {
        return "Product(name='$name',description='$description')"
    }
}

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product where id = :id")
    fun getById(id: Long): Product?

    @Query("SELECT * FROM product")
    fun findAll(): List<Product>

    @Insert
    fun insert(disciplina: Product)

    @Delete
    fun delete(disciplina: Product)
}