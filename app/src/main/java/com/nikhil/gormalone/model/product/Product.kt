package com.nikhil.gormalone.model.product

data class Product(
    val product_id: Long = 0,
    val product_name: String,
    val product_desc: String,
    val product_quantity: String,
    val product_price: String,
    val isUploaded: Boolean
)