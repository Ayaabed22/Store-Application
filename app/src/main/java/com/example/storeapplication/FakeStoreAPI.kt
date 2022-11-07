package com.example.storeapplication

import com.example.storeapplication.cart.data.CartResponse
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.login.LoginRequest
import com.example.storeapplication.login.LoginResponse
import com.example.storeapplication.signUp.SignUpRequest
import com.example.storeapplication.signUp.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FakeStoreAPI {

    @GET("/products")
    fun getProducts() : Call<MutableList<GetProductResponseItem>>

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest ) : Call<LoginResponse>

    @POST("/users")
    fun signUp(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

    @GET("/users/{id}")
    fun getUserData(@Path("id") id:String): Call<GetAllUsersResponse>

    @GET("/users")
    fun getAllUsers(): Call<MutableList<GetAllUsersResponse>>

    @GET("products/{id}")
    fun getProductDetails(@Path("id") id:String): Call<GetProductResponseItem>

    @GET("carts/user/{id}")
    fun getUserCarts(@Path("id") id: Int) : Call<MutableList<CartResponse>>

    @GET("/products/category/{category}")
    fun getProductsInSpecificCategory(@Path("category") category:String):Call<MutableList<GetProductResponseItem>>
}