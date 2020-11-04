package com.nikhil.gormalone.network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

const val BASE_URL_PRODUCTS = "http://15.206.209.151/";

interface ProductsApi {

    @POST("api/addNewProduct")
    suspend fun addNewProduct(@QueryMap map: Map<String, String>) : String

}
