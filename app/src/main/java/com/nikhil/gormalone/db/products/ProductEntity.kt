package com.nikhil.gormalone.db.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val product_id: Long = 0,
    @ColumnInfo(name = "name")
    val product_name: String,
    @ColumnInfo(name = "description")
    val product_desc: String,
    @ColumnInfo(name = "quantity")
    val product_quantity: Int,
    @ColumnInfo(name = "price")
    val product_price: Int,
    @ColumnInfo(name = "isUploaded")
    val isUploaded: Boolean
)
