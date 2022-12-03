package com.example.storeapplication.apiService

import com.example.storeapplication.home.data.GetProductResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {

    @GET("/products")
    suspend fun getProducts() :List<GetProductResponseItem>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id:String): GetProductResponseItem

    @GET("/products/category/{category}")
    suspend fun getProductsInSpecificCategory(@Path("category") category:String):List<GetProductResponseItem>
}