package com.example.storeapplication

import com.example.storeapplication.cart.data.CartResponse
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.login.LoginRequest
import com.example.storeapplication.login.LoginResponse
import com.example.storeapplication.signUp.SignUpRequest
import com.example.storeapplication.signUp.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface FakeStoreAPI {

    @GET("/products")
    fun getProducts() : Call<MutableList<GetProductResponseItem>>

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest ) : Call<LoginResponse>

    @POST("/users")
    fun signUp(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

    @GET("/users")
    fun getAllUsers(): Call<MutableList<GetAllUsersResponse>>

    @GET("carts/user/{id}")
    fun getUserCarts(@Path("id") id: Int) : Call<MutableList<CartResponse>>
}