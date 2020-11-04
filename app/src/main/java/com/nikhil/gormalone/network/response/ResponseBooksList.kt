package com.nikhil.gormalone.network.response

import com.google.gson.annotations.SerializedName

data class ResponseBooksList (
    @SerializedName("results")
    val booksList: List<ResponseBooks>
)

data class ResponseBooks (
    @SerializedName("book_id")
    val book_id: Long,
    @SerializedName("book_name")
    val book_name: String,
    @SerializedName("book_desc")
    val book_desc: String,
    @SerializedName("book_author")
    val book_author: String,
    @SerializedName("book_price")
    val book_price: String,
    @SerializedName("book_img_url")
    val book_img_url: String
)
