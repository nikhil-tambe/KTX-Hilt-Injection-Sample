package com.nikhil.gormalone.model.book

data class Book(
    val book_id: Long,
    val book_name: String,
    val book_desc: String,
    val book_author: String,
    val book_price: String,
    val book_img_url: String
)
