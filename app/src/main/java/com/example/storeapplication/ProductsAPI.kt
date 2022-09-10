package com.example.storeapplication

import retrofit2.Call
import retrofit2.http.GET

interface ProductsAPI {
    @GET("/products")
    fun getProducts() : Call<MutableList<ProductItem>>
}