package com.example.storeapplication

import android.widget.TextView
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

    @GET("products/{id}")
    fun getProductDetails(@Path("id") id:String): Call<GetProductResponseItem>
}