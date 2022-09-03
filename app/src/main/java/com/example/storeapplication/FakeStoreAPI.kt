package com.example.storeapplication

import retrofit2.Call
import retrofit2.http.GET

interface FakeStoreAPI {

    @GET("/products")
    fun getProducts() : Call<MutableList<GetProductResponseItem>>
}