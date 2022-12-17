package com.example.storeapplication.apiService

import com.example.storeapplication.home.data.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsAPI {

    @GET("/products")
    suspend fun getProducts() :List<Product>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id:String): Product

    @GET("/products/category/{category}")
    suspend fun getProductsInSpecificCategory(@Path("category") category:String):List<Product>
}