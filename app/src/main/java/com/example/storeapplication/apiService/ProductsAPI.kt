package com.example.storeapplication.apiService

import com.example.storeapplication.home.data.GetProductResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {

    @GET("/products")
    fun getProducts() : Call<MutableList<GetProductResponseItem>>

    @GET("products/{id}")
    fun getProductDetails(@Path("id") id:String): Call<GetProductResponseItem>

    @GET("/products/category/{category}")
    fun getProductsInSpecificCategory(@Path("category") category:String):Call<MutableList<GetProductResponseItem>>
}