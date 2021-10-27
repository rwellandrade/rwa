package com.aulaandroid.ifoodclone.data

import androidx.room.*

@Entity
class Product(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val category: String,
) : BaseData()

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