package com.nikhil.gormalone.network.request

data class ProductBody(
    val product_name: String,
    val product_desc: String,
    val product_quantity: Int,
    val product_price: Int,
    val user_mobile_no: Long = 9730041804
)
