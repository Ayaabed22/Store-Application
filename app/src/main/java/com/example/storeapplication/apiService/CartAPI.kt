package com.example.storeapplication.apiService

import com.example.storeapplication.cart.data.CartResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartAPI {
    @GET("carts/user/{id}")
    suspend fun getUserCarts(@Path("id") id: Int) : List<CartResponse>
}