package com.nikhil.gormalone.network

import com.nikhil.gormalone.network.response.ResponseBooksList
import retrofit2.http.GET

const val BOOKS_BASE_URL = "http://15.206.209.151/api/"

interface BooksApi {

    @GET("getAllAvailableBooks")
    suspend fun getAllAvailableBooks(): ResponseBooksList

}
